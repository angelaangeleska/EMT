package mk.ukim.finki.emt.lab.service.impl;

import mk.ukim.finki.emt.lab.models.Author;
import mk.ukim.finki.emt.lab.models.Country;
import mk.ukim.finki.emt.lab.repository.AuthorRepository;
import mk.ukim.finki.emt.lab.service.AuthorService;
import mk.ukim.finki.emt.lab.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final CountryService countryService;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryService countryService) {
        this.authorRepository = authorRepository;
        this.countryService = countryService;
    }

    @Override
    public List<Author> listAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> addAuthor(Author author) {
        if (author.getName() != null && !author.getName().isEmpty() &&
                author.getSurname() != null && !author.getSurname().isEmpty() &&
                this.countryService.findCountryById(author.getCountry().getId()).isPresent()) {
            Country country = this.countryService.findCountryById(author.getCountry().getId()).get();
            return Optional.of(this.authorRepository.save(new Author(author.getName(), author.getSurname(), country)));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public List<Author> findByFirstNameOrLastName(String firstName, String lastName) {
        return this.authorRepository.findByNameOrSurname(firstName, lastName);
    }
}
