/* Responsible author: Jacob Martens
 * Contributors:
 */

package dtu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dtu.model.Doctor;


@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
}
