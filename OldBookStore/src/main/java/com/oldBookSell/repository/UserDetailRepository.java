package com.oldBookSell.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oldBookSell.model.UserDetails;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetails,Integer>{
			UserDetails findByEmail(String name);
			boolean	existsByEmail(String name);
			
//			@Query(value="select user_id from user_details where email=?1",nativeQuery = true)
//			Iterable<Integer> getId(String email);
}
