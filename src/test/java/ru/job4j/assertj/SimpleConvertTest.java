package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("1", "2", "3", "4", "5");
        assertThat(list).hasSize(5)
                .isNotNull()
                .first().isEqualTo("1");
        assertThat(list).element(3).isNotNull();
        assertThat(list).last().isNotNull()
                .isEqualTo("5");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("1", "2", "3", "2", "1");
        assertThat(set).hasSize(3)
                .contains("2")
                .containsAnyOf("1")
                .noneMatch(e -> e.equals("5"))
                .last().isNotNull();
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("1", "2");
        assertThat(map).hasSize(2)
                .containsKey("2")
                .containsValue(1)
                .doesNotContainKey("0")
                .containsEntry("1", 0);
    }
}