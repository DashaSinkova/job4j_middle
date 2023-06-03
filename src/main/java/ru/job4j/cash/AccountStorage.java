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
        if (getById(account.getId()).isEmpty()) {
            accounts.put(account.getId(), account);
        } else {
            return false;
        }
       return true;
    }

    public synchronized boolean update(Account account) {
        if (getById(account.getId()).isEmpty()) {
            return false;
        } else {
            accounts.put(account.getId(), account);
        }
        return true;
    }

    public synchronized boolean delete(int id) {
        if (getById(id).isEmpty()) {
            return false;
        } else {
            accounts.remove(id);
        }
        return true;
    }

    public synchronized Optional<Account> getById(int id) {
        Account account = accounts.get(id);
        if (account == null) {
            return Optional.empty();
        }
        return Optional.of(account);
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
