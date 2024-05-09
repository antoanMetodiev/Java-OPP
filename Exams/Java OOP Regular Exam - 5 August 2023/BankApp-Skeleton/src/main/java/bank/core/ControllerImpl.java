package bank.core;

import bank.common.ConstantMessages;
import bank.common.ExceptionMessages;
import bank.entities.bank.Bank;
import bank.entities.bank.BranchBank;
import bank.entities.bank.CentralBank;
import bank.entities.client.Adult;
import bank.entities.client.Client;
import bank.entities.client.Student;
import bank.entities.loan.Loan;
import bank.entities.loan.MortgageLoan;
import bank.entities.loan.StudentLoan;
import bank.repositories.LoanRepository;

import java.util.ArrayList;
import java.util.Collection;

public class ControllerImpl implements Controller {
    private LoanRepository loans;
    private Collection<Bank> banks;

    public ControllerImpl() {
        this.loans = new LoanRepository();
        this.banks = new ArrayList<>();
    }

    @Override
    public String addBank(String type, String name) {
        Bank bank = null;
        if (type.equals("CentralBank")) {
            bank = new CentralBank(name);
        } else if (type.equals("BranchBank")) {
            bank = new BranchBank(name);
        } else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_BANK_TYPE);
        }

        this.banks.add(bank);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_BANK_OR_LOAN_TYPE, type);
    }

    @Override
    public String addLoan(String type) {
        Loan loan = null;
        if (type.equals("StudentLoan")) {
            loan = new StudentLoan();
        } else if (type.equals("MortgageLoan")) {
            loan = new MortgageLoan();
        } else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_LOAN_TYPE);
        }

        this.loans.addLoan(loan);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_BANK_OR_LOAN_TYPE, type);
    }

    @Override
    public String returnedLoan(String bankName, String loanType) {
        Bank bank = this.banks.stream().filter(e -> e.getName()
                .equals(bankName)).findFirst().orElse(null);

        Loan receivedLoan = this.loans.findFirst(loanType);
        if (receivedLoan == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_LOAN_FOUND, loanType));
        }
        this.loans.removeLoan(receivedLoan);
        bank.addLoan(receivedLoan);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_CLIENT_OR_LOAN_TO_BANK, loanType, bankName);
    }

    @Override
    public String addClient(String bankName, String clientType, String clientName, String clientID, double income) {
        Bank bank = this.banks.stream().filter(e -> e.getName()
                .equals(bankName)).findFirst().orElse(null);

        Client client = null;
        if (clientType.equals("Student")) {
            client = new Student(clientName, clientID, income);
        } else if (clientType.equals("Adult")) {
            client = new Adult(clientName, clientID, income);
        } else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_CLIENT_TYPE);
        }

        if (clientType.equals("Student") && bank.getClass().getSimpleName().equals("BranchBank")) {
            bank.addClient(client);
        } else if (clientType.equals("Adult") && bank.getClass().getSimpleName().equals("CentralBank")) {
            bank.addClient(client);
        } else {
            return ConstantMessages.UNSUITABLE_BANK;
        }
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_CLIENT_OR_LOAN_TO_BANK, clientType, bankName);
    }

    @Override
    public String finalCalculation(String bankName) {
        Bank bank = this.banks.stream().filter(e -> e.getName()
                .equals(bankName)).findFirst().orElse(null);

        double incomeTotalSum = bank.getClients().stream().mapToDouble(Client::getIncome).sum();
        double amountsTotalSum = bank.getLoans().stream().mapToDouble(Loan::getAmount).sum();
        return String.format(ConstantMessages.FUNDS_BANK, bankName, incomeTotalSum + amountsTotalSum);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        this.banks.forEach(e -> sb.append(e.getStatistics()));
        return sb.toString().trim();
    }
}
