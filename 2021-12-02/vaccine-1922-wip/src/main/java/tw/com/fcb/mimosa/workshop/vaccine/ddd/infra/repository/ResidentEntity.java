package tw.com.fcb.mimosa.workshop.vaccine.ddd.infra.repository;


import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Data
@Table(name = "RESIDENT")
@Entity
public class ResidentEntity {

  @Id
  @GeneratedValue
  Long id;
  String nhiNo;
  String phoneNo;

  @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
  @JoinColumn(name = "resident_id")
  List<ChooseEntity> chooses;

  @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
  @JoinColumn(name = "resident_id")
  List<CancelEntity> cancels;

}
