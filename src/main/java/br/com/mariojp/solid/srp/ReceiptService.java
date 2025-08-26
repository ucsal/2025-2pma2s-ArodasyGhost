package br.com.mariojp.solid.srp;

public class ReceiptService {

	private final TaxCalculator taxCalculator;
	private final ReceiptFormatter receiptFormatter;

	public ReceiptService() {
		this.taxCalculator = new TaxCalculator();
		this.receiptFormatter = new ReceiptFormatter();

	}

	public String generate(Order order) {

		// Calcula subtotal
		double subtotal = order.getItems().stream().mapToDouble(i -> i.getUnitPrice() * i.getQuantity()).sum();

		// Calcula imposto
		double tax = taxCalculator.calculateTax(subtotal);

		// Total
		double total = subtotal + tax;

		// Formata e retorna o recibo
		return receiptFormatter.format(order, subtotal, tax, total);
	}
}
