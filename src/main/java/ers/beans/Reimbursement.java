package ers.beans;

import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Date;

public class Reimbursement {
	
	private int reimbId;
	private int reimbAmount;
	private Date submitted;
	private Date approved;
	private String description;
	private Blob recipt;
	private int author;
	private int resolver;
	private int statusId;
	private int typeId;
	
	public int getReimbId() {
		return reimbId;
	}
	public void setReimbId(int reimbId) {
		this.reimbId = reimbId;
	}
	public int getReimbAmount() {
		return reimbAmount;
	}
	public void setReimbAmount(int reimbAmount) {
		this.reimbAmount = reimbAmount;
	}
	public Date getSubmitted() {
		return submitted;
	}
	public void setSubmitted(Date submitted) {
		this.submitted = submitted;
	}
	public Date getApproved() {
		return approved;
	}
	public void setApproved(Date approved) {
		this.approved = approved;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Blob getRecipt() {
		return recipt;
	}
	public void setRecipt(Blob recipt) {
		this.recipt = recipt;
	}
	public int getAuthor() {
		return author;
	}
	public void setAuthor(int author) {
		this.author = author;
	}
	public int getResolver() {
		return resolver;
	}
	public void setResolver(int resolver) {
		this.resolver = resolver;
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public Reimbursement(int reimbId, int reimbAmount, Date submitted, Date approved, String description,
			Blob recipt, int author, int resolver, int statusId, int typeId) {
		super();
		this.reimbId = reimbId;
		this.reimbAmount = reimbAmount;
		this.submitted = submitted;
		this.approved = approved;
		this.description = description;
		this.recipt = recipt;
		this.author = author;
		this.resolver = resolver;
		this.statusId = statusId;
		this.typeId = typeId;
	}
	
	public Reimbursement() {
		super();
	}
	
	
}
