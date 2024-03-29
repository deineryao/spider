package com.zimu.spider.yirendai.model;


public class AccountUserInfoModel extends BaseResp
{
	private String accountBalance;
	private String accountStatus;
	private String accumulatedIncome;
	private String email;
	private String investedAmountForExit;
	private String investingAmount;
	private String level;
	private String mobileNo;
	private String name;
	private String token;

	public String getAccountBalance() {
		return this.accountBalance;
	}

	public String getAccountStatus() {
		return this.accountStatus;
	}

	public String getAccumulatedIncome() {
		return this.accumulatedIncome;
	}

	public String getEmail() {
		return this.email;
	}

	public String getInvestedAmountForExit() {
		return this.investedAmountForExit;
	}

	public String getInvestingAmount() {
		return this.investingAmount;
	}

	public String getLevel() {
		return this.level;
	}

	public String getMobileNo() {
		return this.mobileNo;
	}

	public String getName() {
		return this.name;
	}

	public String getToken() {
		return this.token;
	}

	public void setAccountBalance(String accountBalance) {
		this.accountBalance = accountBalance;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public void setAccumulatedIncome(String accumulatedIncome) {
		this.accumulatedIncome = accumulatedIncome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setInvestedAmountForExit(String investedAmountForExit) {
		this.investedAmountForExit = investedAmountForExit;
	}

	public void setInvestingAmount(String investingAmount) {
		this.investingAmount = investingAmount;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "AccountUserInfoModel [accountBalance=" + accountBalance
				+ ", accountStatus=" + accountStatus + ", accumulatedIncome="
				+ accumulatedIncome + ", email=" + email
				+ ", investedAmountForExit=" + investedAmountForExit
				+ ", investingAmount=" + investingAmount + ", level=" + level
				+ ", mobileNo=" + mobileNo + ", name=" + name + ", token="
				+ token + "]";
	}
}
