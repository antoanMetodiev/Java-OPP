package bank.entities.bank;

import bank.common.ExceptionMessages;
import bank.entities.client.Client;
import bank.entities.loan.Loan;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public abstract class BaseBank implements Bank {
    private String name;
    private int capacity;
    private Collection<Loan> loans;
    private Collection<Client> clients;

    public BaseBank(String name, int capacity) {
        this.setName(name);
        this.capacity = capacity;
        this.loans = new ArrayList<>();
        this.clients = new ArrayList<>();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.BANK_NAME_CANNOT_BE_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public Collection<Client> getClients() {
        return this.clients;
    }

    @Override
    public Collection<Loan> getLoans() {
        return this.loans;
    }

    @Override
    public void addClient(Client client) {
        if (this.clients.size() >= this.capacity) {
            throw new IllegalStateException(ExceptionMessages.NOT_ENOUGH_CAPACITY_FOR_CLIENT);
        }
        this.clients.add(client);
    }

    @Override
    public void removeClient(Client client) {
        this.clients.remove(client);
    }

    @Override
    public void addLoan(Loan loan) {
        this.loans.add(loan);
    }

    @Override
    public int sumOfInterestRates() {
        return this.loans.stream().mapToInt(Loan::getInterestRate).sum();
    }

    @Override
    public String getStatistics() {
//        "Name: {bankName}, Type: {bankType}
//        Clients: {clientName1}, {clientName2} ... / Clients: none
//        Loans: {loansCount}, Sum of interest rates: {sumOfInterestRates}"

        StringBuilder sb = new StringBuilder(String.format("Name: %s, Type: %s"
                , this.name, this.getClass().getSimpleName())).append(System.lineSeparator());

        // add Clients:
        sb.append("Clients: ");
        if (this.clients.isEmpty()) {
            sb.append("none").append(System.lineSeparator());
        } else {
            sb.append(this.clients.stream().map(Client::getName)
                    .collect(Collectors.joining(", ")));
            sb.append(System.lineSeparator());
        }

        sb.append(String.format("Loans: %d, Sum of interest rates: %d"
                , this.loans.size(), this.sumOfInterestRates()));

        sb.append((System.lineSeparator()));

        return sb.toString();
    }
}
