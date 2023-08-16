package example.micronaut;

import io.lettuce.core.api.StatefulRedisConnection;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import jakarta.inject.Inject;

import java.util.UUID;

@MicronautTest
class TestResourcesRedisFailTest {

    private StatefulRedisConnection<String, String> redisConnection;

    public TestResourcesRedisFailTest(StatefulRedisConnection<String, String> redisConnection) {
        this.redisConnection = redisConnection;
    }

    @Test
    void testRedis() {
        var key = UUID.randomUUID();
        redisConnection.sync().set(key.toString(), "argle");
        var value = redisConnection.sync().get(key.toString());

        Assertions.assertEquals("argle", value);
    }
}
