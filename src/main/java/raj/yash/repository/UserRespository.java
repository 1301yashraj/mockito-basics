package raj.yash.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

import raj.yash.model.User;

import java.util.List;


@Repository
public interface UserRespository extends JpaRepository<User, String> {
    User findFirstByOrderByFullNameAsc();
    Page<User> findAll(Pageable pageable);
    Streamable<User> findByCompanyContaining(String text);



    @Query("Select count(u) from User u where u.city = :c and u.title = :contactTitle") //?1 or :city orvalue passed in 1st parameter
    //you cant mix and mact :c with ?2 either use 1 ,2 or parameter name
    int findAllByCity(@Param("c") String city, String contactTitle);


    //While wrtitng native query the query should Look like what you can execute on sql
    @Query(value = "Select count(city) from customers u where u.city = ?1", nativeQuery = true)
    int findAllByCityNative(String city);

//    Spring Expression Language to write Query >> Query3
    @Query("select u.fullName, u.postalCode from #{#entityName} u where u.title like %?1%")
    List<Object[]> findByAsArrayAndSort(String text, Sort sort);
    //for sorting we use the User class and its property names
    //only for native query we have to write the actual table name, column name an all


}
