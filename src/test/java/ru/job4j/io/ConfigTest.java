package ru.job4j.io;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConfigTest {
    @Test
    void whenPairWithComment() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("password")).isEqualTo("password");
        assertThat(config.value("key")).isEqualTo("value==");
        assertThat(config.value("name")).isNull();
    }

    @Test
    void whenPairEqualsOnly() {
        String path = "./data/pair_with_equals_only.properties";
        Config config = new Config(path);
        assertThrows(IllegalArgumentException.class, config::load);
    }

    @Test
    void whenPairEmptyKey() {
        String path = "./data/pair_with_empty_key.properties";
        Config config = new Config(path);
        assertThrows(IllegalArgumentException.class, config::load);
    }

    @Test
    void whenPairWitoutEquals() {
        String path = "./data/pair_without_equals.properties";
        Config config = new Config(path);
        assertThrows(IllegalArgumentException.class, config::load);
    }

    @Test
    void whenPairEmptyValue() {
        String path = "./data/pair_with_empty_value.properties";
        Config config = new Config(path);
        assertThrows(IllegalArgumentException.class, config::load);
    }
}