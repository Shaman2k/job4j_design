package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisUnknown() {
        Box box = new Box(1, 0);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object");
    }

    @Test
    void isExist() {
        Box box = new Box(8, 4);
        boolean exist = box.isExist();
        assertThat(exist).isTrue();
    }

    @Test
    void isNotExist() {
        Box box = new Box(1, 0);
        boolean exist = box.isExist();
        assertThat(exist).isFalse();
    }

    @Test
    void whenEdgeLessThanZeroThenVertexIsMinusOne() {
        Box box = new Box(4, -5);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isEqualTo(-1);
    }

    @Test
    void whenUnknownThenVertexIsMinusOne() {
        Box box = new Box(3, 5);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isEqualTo(-1);
    }

    @Test
    void whenTetrahedronAndEdgeFourThenThenAreaNinetySix() {
        Box box = new Box(4, 4);
        double area = box.getArea();
        assertThat(area).isCloseTo(27.71, offset(0.01D));
    }

    @Test
    void whenUnknownThenAreaIsZero() {
        Box box = new Box(3, 3);
        double area = box.getArea();
        assertThat(area).isEqualTo(0D);
    }
}