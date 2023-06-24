package Entidades;

import java.util.Comparator;

public class UserTweetsComparator implements Comparator<User> {

    @Override
    public int compare(User firstUser, User secondUser) {
        return Integer.compare(firstUser.getTweetsCount(), secondUser.getTweetsCount());
    }

}