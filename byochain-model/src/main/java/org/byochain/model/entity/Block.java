package org.byochain.model.entity;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entity bean Block for table block
 * @author Giuseppe Vincenzi
 *
 */
@Entity
@Table(name = "block")
public class Block implements Comparable<Block>{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "block_id")
	private Long id;

	@Column(name = "hash")
	private String hash;

	@Column(name = "previous_hash")
	private String previousHash;

	@Column(name = "timestamp")
	private Calendar timestamp;

	@Column(name = "data")
	private String data;
	
	@Column(name = "nonce")
	private Integer nonce;
	
	@ManyToOne
    @JoinColumn(name="miner_id", nullable=false)
	private User miner;
	
	/**
	 * Mapped by join table block_validation_user
	 */
	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "block_validation_user", 
        joinColumns = { @JoinColumn(name = "block_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
	private Set<User> validators;

	public Block() {
	}

	public Block(String data, String previousHash) {
		setPreviousHash(previousHash);
		setData(data);
		setTimestamp(Calendar.getInstance());
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the hash
	 */
	public String getHash() {
		return hash;
	}

	/**
	 * @param hash
	 *            the hash to set
	 */
	public void setHash(String hash) {
		this.hash = hash;
	}

	/**
	 * @return the previousHash
	 */
	public String getPreviousHash() {
		return previousHash;
	}

	/**
	 * @param previousHash
	 *            the previousHash to set
	 */
	private void setPreviousHash(String previousHash) {
		this.previousHash = previousHash;
	}

	/**
	 * @return the timestamp
	 */
	public Calendar getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp
	 *            the timestamp to set
	 */
	private void setTimestamp(Calendar timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	private void setData(String data) {
		this.data = data;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((hash == null) ? 0 : hash.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((previousHash == null) ? 0 : previousHash.hashCode());
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
		result = prime * result + ((nonce == null) ? 0 : nonce.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Block other = (Block) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (hash == null) {
			if (other.hash != null)
				return false;
		} else if (!hash.equals(other.hash))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (previousHash == null) {
			if (other.previousHash != null)
				return false;
		} else if (!previousHash.equals(other.previousHash))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (nonce == null) {
			if (other.nonce != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Block [data=" + data + ", nonce=" + nonce + ", hash=" + hash + ", previousHash=" + previousHash + ", timestamp="
				+ timestamp.getTime() + "]";
	}

	@Override
	public int compareTo(Block arg0) {
		return getTimestamp().compareTo(arg0.getTimestamp());
	}

	/**
	 * @return the nonce
	 */
	public Integer getNonce() {
		return nonce;
	}

	/**
	 * @param nonce the nonce to set
	 */
	public void setNonce(Integer nonce) {
		this.nonce = nonce;
	}

	/**
	 * @return the miner
	 */
	public User getMiner() {
		return miner;
	}

	/**
	 * @param miner the miner to set
	 */
	public void setMiner(User miner) {
		this.miner = miner;
	}

	/**
	 * @return the validators
	 */
	public Set<User> getValidators() {
		if(validators == null){
			setValidators(new HashSet<>(0));
		}
		return validators;
	}

	/**
	 * @param validators the validators to set
	 */
	public void setValidators(Set<User> validators) {
		this.validators = validators;
	}
}
