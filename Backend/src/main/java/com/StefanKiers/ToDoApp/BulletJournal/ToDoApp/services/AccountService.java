package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.services;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.account.AccountCreateDTO;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.account.AccountResponseDTO;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.models.Account;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

/**
 * AccountService
 * Challenge: com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.services
 *
 * @author Stefan Kiers
 * @since 11-1-2026
 */
@Service
public class AccountService {
    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(PasswordEncoder passwordEncoder, AccountRepository accountRepository) {
        this.passwordEncoder = passwordEncoder;
        this.accountRepository = accountRepository;
    }

    public AccountResponseDTO accountResponseDTO(Account account) {
        return new AccountResponseDTO(
                account.getId(),
                account.getUsername(),
                account.getDisplayName(),
                account.getCreationDate());
    }

    public void createAccount(AccountCreateDTO request) {
        Account account = new Account();
        account.setUsername(request.username());

        String hashed = passwordEncoder.encode(request.password());
        account.setPassword(hashed);

        account.setDisplayName(request.displayName());
        account.setCreationDate(OffsetDateTime.now());
        accountRepository.save(account);
    }

    public List<AccountResponseDTO> findAll() {
        return this.accountRepository.findAll()
                .stream()
                .map(this::accountResponseDTO)
                .toList();
    }

    public Optional<AccountResponseDTO> findById(Long id) {
        return this.accountRepository.findById(id)
                .map(this::accountResponseDTO);
    }
}
