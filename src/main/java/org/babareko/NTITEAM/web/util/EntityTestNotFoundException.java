package org.babareko.NTITEAM.web.util;

public class EntityTestNotFoundException extends Exception {
    private Integer id;

    public EntityTestNotFoundException(Integer id) {
        super(String.format("Entity is not found with id : '%s'", id));
    }
}
