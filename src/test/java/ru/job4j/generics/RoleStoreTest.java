package ru.job4j.generics;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RoleStoreTest {
    @Test
    public void whenAddRoleThenRoleNameIsAdmin() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Admin"));
        Role result = roleStore.findById("1");
        assertThat(result.getName()).isEqualTo("Admin");
    }

    @Test
    public void whenAddAndFindThenRoleIsNull() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Admin"));
        Role result = roleStore.findById("10");
        assertThat(result).isNull();
    }

    @Test
    public void whenAddDuplicateAndFindRoleIsAdmin() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Admin"));
        roleStore.add(new Role("1", "User"));
        Role result = roleStore.findById("1");
        assertThat(result.getName()).isEqualTo("Admin");
    }

    @Test
    public void whenReplaceThenRoleIsUser() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Admin"));
        boolean replaced = roleStore.replace("1", new Role("1", "User"));
        Role result = roleStore.findById("1");
        assertThat(replaced).isTrue();
        assertThat(result.getName()).isEqualTo("User");
    }

    @Test
    public void whenReplaceNonExistingRoleThenReturnFalse() {
        RoleStore roleStore = new RoleStore();
        Role role = new Role("2", "User");
        boolean replaced = roleStore.replace("3", role);
        assertThat(replaced).isFalse();
    }

    @Test
    public void whenDeleteExistingRoleThenRoleIsDeleted() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Admin"));
        roleStore.delete("1");
        Role result = roleStore.findById("1");
        assertThat(result).isNull();
    }

    @Test
    public void whenDeleteNonExistingRoleThenNothingHappens() {
        RoleStore roleStore = new RoleStore();
        Role role1 = new Role("1", "Admin");
        roleStore.add(role1);
        roleStore.delete("2");
        Role result = roleStore.findById("1");
        assertThat(result).isNotNull();
    }
}