package jp.TsudaJun.spring.mysample.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import jp.TsudaJun.spring.mysample.validation.AlreadyExists;
import jp.TsudaJun.spring.mysample.validation.NoEmpty;

@Entity
@Table (name="user")
public class User {


		@Id
		@Column
		@NoEmpty(message="ユーザーIDを入力してください")
		@AlreadyExists
	    private String userid;
		
		@Column
		@NoEmpty(message="パスワードを入力してください")
		private String password;
		
		@Column
		@NoEmpty(message="ユーザーネームを入力してください")
		private String username;
		
		@Column(insertable = false, updatable = false)
		private Timestamp ins_ymd;

		@Column(insertable = false, updatable = true)
		private Timestamp upd_ymd;
		
		@OneToMany(cascade = CascadeType.ALL)
		@JoinColumn(name="userid", referencedColumnName = "userid" )
		private List<Item> items = null;
		
		@OneToMany(cascade = CascadeType.ALL)
		@JoinColumn(name="userid", referencedColumnName = "userid" )
		private List<Sell> sells = null;
		
		@OneToMany(cascade = CascadeType.ALL)
		@JoinColumn(name="userid", referencedColumnName = "userid" )
		private List<UserAddress> addresses = null;
		
		
		public void setUserid(String userid){
			this.userid = userid;
		}

		public String getUserid(){
			return userid;
		}
		
		public void setUsername(String username){
			this.username = username;
		}

		public String getUsername(){
			return username;
		}
		
		public void setPassword(String password){
			this.password = password;
		}

		public String getPassword(){
			return password;
		}

}
