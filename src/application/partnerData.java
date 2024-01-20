package application;

import java.math.BigDecimal;
import java.sql.Date;

public class partnerData {

	private Integer partnerId; 
	private String partnerName;
	private String organizationType;
	private BigDecimal fundsAvailable;
	private Integer employeesAvailable;
	private String mainContacter;
	private String contacterPhoneNumber;
	private String contacterEmail;
	private Date dateJoined;
	
	public partnerData(Integer partnerId, String partnerName, String organizationType, BigDecimal fundsAvailable, Integer employeesAvailable, String mainContacter, String contacterPhoneNumber, String contacterEmail, Date dateJoined)
	{
		this.partnerId = partnerId;
		this.partnerName = partnerName;
		this.organizationType = organizationType;
		this.fundsAvailable = fundsAvailable;
		this.employeesAvailable = employeesAvailable;
		this.mainContacter = mainContacter;
		this.contacterPhoneNumber = contacterPhoneNumber;
		this.contacterEmail = contacterEmail;
		this.dateJoined = dateJoined; 
	}
	
	public Integer getPartnerId()
	{
		return partnerId;
	}
	
	public String getPartnerName()
	{
		return partnerName;
	}
	
	public String getOrganizationType()
	{
		return organizationType; 
	}
	
	public BigDecimal getFundsAvailable()
	{
		return fundsAvailable;
	}
	
	public Integer getEmployeesAvailable()
	{
		return employeesAvailable;
	}
	
	public String getMainContacter()
	{
		return mainContacter;
	}
	
	public String getContacterPhoneNumber()
	{
		return contacterPhoneNumber; 
	}
	
	public String getContacterEmail()
	{
		return contacterEmail;
	}
	
	public Date getDateJoined()
	{
		return dateJoined;
	}
}
