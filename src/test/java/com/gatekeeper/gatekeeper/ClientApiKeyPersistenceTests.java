package com.gatekeeper.gatekeeper;

import com.gatekeeper.gatekeeper.domain.ApiKey;
import com.gatekeeper.gatekeeper.domain.Client;
import com.gatekeeper.gatekeeper.repository.ApiKeyRepository;
import com.gatekeeper.gatekeeper.repository.ClientRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowableOfType;

@SpringBootTest
@Transactional
class ClientApiKeyPersistenceTests {

    @Autowired
    private ClientRepository clients;

    @Autowired
    private ApiKeyRepository apiKeys;

    @Autowired
    private EntityManager entityManager;

    @Test
    void persistsClientLimitAndKeyHashWithTheirRelationship() {
        Client client = clients.save(new Client("Example", 100));
        String hash = "a".repeat(64);
        Long keyId = apiKeys.saveAndFlush(new ApiKey(client, hash, true)).getId();
        entityManager.clear();

        ApiKey storedKey = apiKeys.findById(keyId).orElseThrow();
        assertThat(storedKey.getKeyHash()).isEqualTo(hash);
        assertThat(storedKey.isActive()).isTrue();
        assertThat(storedKey.getClient().getId()).isEqualTo(client.getId());
        assertThat(storedKey.getClient().getRequestLimit()).isEqualTo(100);
    }

    @Test
    void allowsInactiveKeysForTheSameClientAndAnActiveKeyForAnotherClient() {
        Client first = clients.save(new Client("First", 100));
        Client second = clients.save(new Client("Second", 200));

        apiKeys.saveAndFlush(new ApiKey(first, "a".repeat(64), true));
        apiKeys.saveAndFlush(new ApiKey(first, "b".repeat(64), false));
        apiKeys.saveAndFlush(new ApiKey(first, "c".repeat(64), false));
        apiKeys.saveAndFlush(new ApiKey(second, "d".repeat(64), true));
    }

    @Test
    void rejectsASecondActiveKeyForTheSameClient() {
        Client client = clients.save(new Client("Example", 100));
        apiKeys.saveAndFlush(new ApiKey(client, "a".repeat(64), true));

        DataIntegrityViolationException failure = catchThrowableOfType(
                DataIntegrityViolationException.class,
                () -> apiKeys.saveAndFlush(new ApiKey(client, "b".repeat(64), true)));

        assertThat(failure).isNotNull();
        assertThat(failure.getMostSpecificCause()).isInstanceOfSatisfying(PSQLException.class, cause -> {
            assertThat(cause.getSQLState()).isEqualTo("23505");
            assertThat(cause.getServerErrorMessage().getConstraint()).isEqualTo("uq_api_keys_active_client");
        });
    }
}
