package ers.beans;

import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Date;

public class Reimbursement {

	private int reimbId;
	private double reimbAmount;
	private Date submitted;
	private Date resolved;
	private String description;
	private Blob recipt;
	private User author;
	private User resolver;
	private ReimbursementStatus status;
	private ReimbursementType type;

	public Reimbursement() {
		super();
	}

	public Reimbursement(int reimbId, double reimbAmount, Date submitted, Date resolved, String description, Blob recipt,
			User author, User resolver, ReimbursementStatus status, ReimbursementType type) {
		super();
		this.reimbId = reimbId;
		this.reimbAmount = reimbAmount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.recipt = recipt;
		this.author = author;
		this.resolver = resolver;
		this.status = status;
		this.type = type;
	}

	public int getReimbId() {
		return reimbId;
	}

	public void setReimbId(int reimbId) {
		this.reimbId = reimbId;
	}

	public double getReimbAmount() {
		return reimbAmount;
	}

	public void setReimbAmount(double reimbAmount) {
		this.reimbAmount = reimbAmount;
	}

	public Date getSubmitted() {
		return submitted;
	}

	public void setSubmitted(Date submitted) {
		this.submitted = submitted;
	}

	public Date getResolved() {
		return resolved;
	}

	public void setResolved(Date resolved) {
		this.resolved = resolved;
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

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public User getResolver() {
		return resolver;
	}

	public void setResolver(User resolver) {
		this.resolver = resolver;
	}

	public ReimbursementStatus getStatus() {
		return status;
	}

	public void setStatus(ReimbursementStatus status) {
		this.status = status;
	}

	public ReimbursementType getType() {
		return type;
	}

	public void setType(ReimbursementType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Reimbursement [reimbId=" + reimbId + ", reimbAmount=" + reimbAmount + ", submitted=" + submitted
				+ ", resolved=" + resolved + ", description=" + description + ", recipt=" + recipt + ", author="
				+ author + ", resolver=" + resolver + ", status=" + status + ", type=" + type + "]";
	}

}