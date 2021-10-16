package springBootJdbc.sbjdbc.dao;

import java.util.List;
import javax.sql.DataSource;
import springBootJdbc.sbjdbc.mapper.BankAccountMapper;
import springBootJdbc.sbjdbc.model.BankAccountInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import springBootJdbc.sbjdbc.exception.BackTransactionException;

@Repository
@Transactional
public class BankAccountDAO extends JdbcDaoSupport {
	
	@Autowired
	public BankAccountDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}
	
	public List<BankAccountInfo> getBankAccounts(){
		String sql = BankAccountMapper.BASE_SQL;
		
		Object[] params = new Object[] {};
		BankAccountMapper mapper = new BankAccountMapper();
		List<BankAccountInfo> list = this.getJdbcTemplate().query(sql, params,mapper);
		
		return list;	
	}
	
	public BankAccountInfo findBackAccount(Long id) throws EmptyResultDataAccessException{
		String sql = "SELECT ID, FULL_NAME, BALANCE FROM BANK_ACCOUNT WHERE ID = ?";
		
		Object[] param = new Object[] {id};
		BankAccountMapper mapper = new BankAccountMapper();
		try{
			BankAccountInfo bankAccount = this.getJdbcTemplate().queryForObject(sql, param, mapper);
			return bankAccount;
		}catch(EmptyResultDataAccessException e) {
			return null;
		}
	
	}
	
	// Add Money
	@Transactional(propagation = Propagation.MANDATORY )
	public void addAmount(Long id, double amount) throws BackTransactionException{
		BankAccountInfo accountInfo = this.findBackAccount(id);
		if(accountInfo == null) {
			// Bao loi khong tim thay tai khoan ngan hang
		}
		double newBalance = accountInfo.getBalance() + amount;
		if(newBalance < 0) {
			// Bao loi vi tien am
		}
		accountInfo.setBalance(newBalance);
		// Update into DB
		String sqlUpdate = "UPDATE BANK_ACCOUNT SET BALANCE = ? WHERE ID = ?";
		this.getJdbcTemplate().update(sqlUpdate, accountInfo.getBalance(), accountInfo.getId());
		
	}
	
	// Send Money
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor =BackTransactionException.class)
	public void sendMoney(Long fromAccountId, Long toAccountId, double amount) throws BackTransactionException {
		addAmount(toAccountId, amount);
		addAmount(fromAccountId, -amount );
	}
}
