package com.emer.api.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Component;

import com.emer.api.model.Transaction;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Component
public class InvoicePdfBuilder {

	// Various fonts used
	Font boldFont = new Font(Font.FontFamily.HELVETICA, 25, Font.BOLD);
	Font invFont = new Font(Font.FontFamily.HELVETICA, 45, Font.BOLD);
	Font titleFont = new Font(Font.FontFamily.HELVETICA, 15, Font.BOLD);
	Font boldSmall = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);

	/**
	 * s
	 * 
	 * @param inv
	 * @param salon
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 */
	public byte[] createPdf(Transaction inv) throws IOException, DocumentException {

		Document document = new Document(PageSize.A4.rotate());
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PdfWriter.getInstance(document, baos);

		document.open();

		// Large Invoice Text
		PdfPTable invoiceTitleText = createPdfInvoiceTitle(inv);
		document.add(invoiceTitleText);

		// Business Address and Bill To Details
		Paragraph addp = new Paragraph("The Hair and Barber's Market", boldFont);
		Paragraph addp1 = new Paragraph("Belville,");
		Paragraph addp2 = new Paragraph("Athenry,");
		Paragraph addp3 = new Paragraph("Co.Galway,");
		Paragraph addp4 = new Paragraph("H65 DV78");
		Paragraph addp5 = new Paragraph("VAT Reg: 3399284CH");
		addp5.setSpacingAfter(25f);
		Paragraph addc = new Paragraph("Bill To:", titleFont);
		Paragraph addc1 = new Paragraph(inv.getTradeEntityName());
		addc1.setSpacingAfter(72f);

		document.add(addp);
		document.add(addp1);
		document.add(addp2);
		document.add(addp3);
		document.add(addp4);
		document.add(addp5);
		document.add(addc);
		document.add(addc1);

		// The Invoice Items Table
		PdfPTable table = setUpTableForItems();
		this.setItemsTableHeader(table);

		// Make sure table always has pages
		this.addDefaultTableRow(table);

		// Adding the invoice items
		this.addInvoiceItems(inv, table);

		// Add invoice vat and totals
		this.addVatAndTotals(inv, table);

		// Space after table
		table.setSpacingAfter(72f);
		document.add(table);

		// Close PDF
		document.close();

		byte[] bytes = baos.toByteArray();
		return bytes;
	}

	/**
	 * 
	 * @param inv
	 * @param table
	 */
	private void addVatAndTotals(Transaction inv, PdfPTable table) {
		// EX VAT
		PdfPCell cellTotal = new PdfPCell();
		cellTotal.setColspan(2);
		cellTotal.setBorder(PdfPCell.NO_BORDER);
		table.addCell(cellTotal);

		PdfPCell cellTotalTitle = new PdfPCell(new Phrase("Ex-VAT: ", titleFont));
		cellTotalTitle.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cellTotalTitle.setColspan(1);
		table.addCell(cellTotalTitle);

		// String formattedExVat = df.format(Float.parseFloat(inv.getTotalExVat()));
		PdfPCell cellTotalAmount = new PdfPCell(new Phrase(inv.getTotalExVat().toString(), boldSmall));
		cellTotalAmount.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cellTotalAmount.setColspan(1);
		table.addCell(cellTotalAmount);

		// VAT
		PdfPCell cellVat = new PdfPCell();
		cellVat.setColspan(2);
		cellVat.setBorder(PdfPCell.NO_BORDER);
		table.addCell(cellVat);

		PdfPCell cellVatTitle = new PdfPCell(new Phrase("VAT: ", titleFont));
		cellVatTitle.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cellVatTitle.setColspan(1);
		table.addCell(cellVatTitle);

		// String formattedVat = df.format(Float.parseFloat(inv.getVat()));
		PdfPCell cellVatAmount = new PdfPCell(new Phrase(inv.getVat().toString(), boldSmall));
		cellVatAmount.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cellVatAmount.setColspan(1);
		table.addCell(cellVatAmount);

		// TOTAL
		PdfPCell cellTotalInclVat = new PdfPCell();
		cellTotalInclVat.setColspan(2);
		cellTotalInclVat.setBorder(PdfPCell.NO_BORDER);
		table.addCell(cellTotalInclVat);

		PdfPCell cellTotalTitleInclVat = new PdfPCell(new Phrase("Total: ", titleFont));
		cellTotalTitleInclVat.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cellTotalTitleInclVat.setColspan(1);
		table.addCell(cellTotalTitleInclVat);

		// String formattedTotal = df.format(Float.parseFloat(inv.getTotal()));
		PdfPCell cellTotalAmountInclVat = new PdfPCell(new Phrase(inv.getTotal().toString(), boldSmall));
		cellTotalAmountInclVat.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cellTotalAmountInclVat.setColspan(1);
		table.addCell(cellTotalAmountInclVat);
	}

	/**
	 * 
	 * @param inv
	 * @param table
	 */
	private void addInvoiceItems(Transaction inv, PdfPTable table) {
		for (int counter = 0; counter < inv.getItems().size(); counter++) {
			table.addCell(inv.getItems().get(counter).getName());
			table.addCell(inv.getItems().get(counter).getQty().toString());
			table.addCell(inv.getItems().get(counter).getPrice().toString());
			table.addCell(inv.getItems().get(counter).getTotal().toString());
		}
	}

	/**
	 * 
	 * @param table
	 */
	private void addDefaultTableRow(PdfPTable table) {
		table.addCell("");
		table.addCell("");
		table.addCell("");
		table.addCell("");
	}

	/**
	 * 
	 * @param table
	 */
	private void setItemsTableHeader(PdfPTable table) {
		table.getDefaultCell().setBackgroundColor(new GrayColor(0.75f));

		table.addCell(getItemsTableTitleCell("Item Description"));
		table.addCell(getItemsTableTitleCell("Qty"));
		table.addCell(getItemsTableTitleCell("Price"));
		table.addCell(getItemsTableTitleCell("Total"));

		table.getDefaultCell().setBackgroundColor(GrayColor.GRAYWHITE);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		table.getDefaultCell().setPadding(3);
	}

	/**
	 * 
	 * @return
	 */
	private PdfPTable setUpTableForItems() {
		float[] columnWidths = { 7, 1, 1, 2 };
		PdfPTable table = new PdfPTable(columnWidths);
		table.setWidthPercentage(100);
		table.getDefaultCell().setUseAscender(true);
		table.getDefaultCell().setUseDescender(true);
		return table;
	}

	/**
	 * 
	 * @param inv
	 * @return
	 */
	private PdfPTable createPdfInvoiceTitle(Transaction inv) {
		PdfPTable invoiceTitleText = new PdfPTable(1);
		invoiceTitleText.setWidthPercentage(100);
		invoiceTitleText.addCell(getCell("INVOICE", PdfPCell.ALIGN_RIGHT, invFont));
		invoiceTitleText.addCell(getCell("#" + inv.getId().toString(), PdfPCell.ALIGN_RIGHT, boldFont));
		return invoiceTitleText;
	}

	/**
	 * 
	 * @param text
	 * @param alignment
	 * @param f
	 * @return
	 */
	public PdfPCell getCell(String text, int alignment, Font f) {
		PdfPCell cell = new PdfPCell(new Phrase(text, f));
		cell.setPadding(0);
		cell.setHorizontalAlignment(alignment);
		cell.setBorder(PdfPCell.NO_BORDER);
		return cell;
	}

	// Create title cells for the invoice items table
	/**
	 * 
	 * @param text
	 * @return
	 */
	public PdfPCell getItemsTableTitleCell(String text) {
		Font f = new Font(FontFamily.HELVETICA, 15, Font.NORMAL, GrayColor.GRAYWHITE);
		PdfPCell cell = new PdfPCell(new Phrase(text, f));
		cell.setBackgroundColor(GrayColor.DARK_GRAY);
		return cell;
	}
}
