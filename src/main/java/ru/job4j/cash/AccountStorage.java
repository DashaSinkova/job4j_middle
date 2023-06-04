package ru.job4j.cash;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Optional;

@ThreadSafe
public class AccountStorage {

    @GuardedBy("this")
    private final HashMap<Integer, Account> accounts = new HashMap<>();

    public synchronized boolean add(Account account) {
       return Optional.ofNullable(accounts.putIfAbsent(account.getId(), account)).isEmpty();
    }

    public synchronized boolean update(Account account) {
        return Optional.ofNullable(accounts.replace(account.getId(), account)).isPresent();
    }

    public synchronized boolean delete(int id) {
        return Optional.ofNullable(accounts.remove(id)).isPresent();
    }

    public synchronized Optional<Account> getById(int id) {
        return Optional.ofNullable(accounts.get(id));
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        Optional<Account> source = getById(fromId);
        Optional<Account> dest = getById(toId);
        if (source.isPresent() && dest.isPresent() && source.get().amount() >= amount) {
            source.get().setAmount(source.get().amount() - amount);
            dest.get().setAmount(dest.get().amount() + amount);
            return true;
        }
        return false;
    }
}
