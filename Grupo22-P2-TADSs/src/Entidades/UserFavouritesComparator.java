package Entidades;

import java.util.Comparator;

public class UserFavouritesComparator implements Comparator<User> {

    @Override
    public int compare(User firstUser, User secondUser) {
        return Integer.compare( secondUser.getFavoritesCount(),firstUser.getFavoritesCount());
    }

}