/* Responsible author: Tobias Collin 
 * Contributors:
 */

package dtu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dtu.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Integer> {
}
