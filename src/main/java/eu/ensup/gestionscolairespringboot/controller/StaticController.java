package eu.ensup.gestionscolairespringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import eu.ensup.gestionscolairespringboot.domaine.Cours;
import eu.ensup.gestionscolairespringboot.domaine.Etudiant;
import eu.ensup.gestionscolairespringboot.service.EtudiantService;
import eu.ensup.gestionscolairespringboot.service.IEtudiantService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Khady, Benjamin and David Controller contenant les différentes
 *         méthodes permettant les interactions service <=> vues
 */
@Controller
public class StaticController {

	@Autowired
	IEtudiantService ietudiantservice;

	/**
	 * @return
	 */
	@Bean
	public EtudiantService ietudiantservice() {
		return new EtudiantService();
	}

	/**
	 * 
	 */
	public StaticController() {
		super();
	}

	/**
	 * @return
	 */
	public IEtudiantService getIetudiantservice() {
		return ietudiantservice;
	}

	/**
	 * @param ietudiantservice
	 */
	public void setIetudiantservice(IEtudiantService ietudiantservice) {
		this.ietudiantservice = ietudiantservice;
	}

	/**
	 * @param iformationService
	 */
	public StaticController(IEtudiantService iformationService) {
		super();
		this.ietudiantservice = iformationService;
	}
	
	@GetMapping("/accessDenied")
    public String accessDenied(Model model) 
    {
        return "accessDenied";
    }

	/**
	 * Méthode listant les étudiants
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/listeEtudiants")
	public String listeEtudiants(Model model) {
		System.out.println("entree dans la methode listeEtudiants");
		model.addAttribute("listeEtudiants", ietudiantservice.getAll());
		return "listeEtudiants";
	}

	/**
	 * Méthode listant les cours
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/listeCours")
	public String listeCours(Model model) {
		System.out.println("entree dans la methode listeEtudiants");
		model.addAttribute("listeCours", ietudiantservice.getAllCours());
		return "listeCours";
	}

	/**
	 * redirection vers la page d'accueil
	 * 
	 * @return
	 */
	@GetMapping({ "/", "/accueil" })
	public String accueil() {

		return "accueil";
	}
	
	@GetMapping("/getFormAjoutEtudiantCours")
	public String getFormAjoutEtudiantCours(Model model) {
		model.addAttribute("listeEtudiants", ietudiantservice.getAll());
		model.addAttribute("listeCours", ietudiantservice.getAllCours());
		return "ajouterEtudiantCours";
	}

	/**
	 * permet de lier un étudiant à un cours redirige vers la vue
	 * messageAjoutEtudiantCours.jsp
	 * 
	 * @param etudiant
	 * @param cours
	 * @return
	 */
	@PostMapping("/lierEtudiantCours")
	public String lierEtudiantCours(Etudiant etudiant, Cours cours) {
		System.out.println("entree dans la methode ajouterEtudiant");
		ietudiantservice.lierCoursEtudiant(cours, etudiant);
		return "messageAjoutEtudiantCours";
	}

	/**
	 * Redirection à la vue ajouterEtudiant.jsp
	 * 
	 * @return
	 */
	@GetMapping("getFormAjoutEtudiant")
	public String getFormAjoutEtudiant() {
		System.out.println("get ajout etudiant ctrl");
		return "ajouterEtudiant";
	}

	/**
	 * Méthode permettant de sauvegarder un étudiant dans la base en remplissant les
	 * différents attributs suivant :
	 * 
	 * @param nom
	 * @param prenom
	 * @param telephone
	 * @param adresse
	 * @param mail
	 * @param dateNaissance
	 * @param etudiant
	 * @param modelMap
	 * @return
	 * 
	 *         Redirige sur la vue listeEtudiants.jsp
	 */

	@ApiOperation(value = "Sauvegarde d'un étudiant")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping("/saveEtudiant")
	public String saveEtudiant(@RequestParam("nom") String nom, @RequestParam("prenom") String prenom,
			@RequestParam("telephone") int telephone, @RequestParam("adresse") String adresse,
			@RequestParam("mail") String mail, @RequestParam("dateNaissance") String dateNaissance, Etudiant etudiant,
			ModelMap modelMap) {
		System.out.println("post ajout etudiant ctrl");
		etudiant.setNom(nom);
		etudiant.setPrenom(prenom);
		etudiant.setAdresse(adresse);
		etudiant.setTelephone(telephone);
		etudiant.setDateNaissance(dateNaissance);
		ietudiantservice.saveStudent(etudiant);

		return "redirect:/listeEtudiants";
	}

	/**
	 * redirige vers la vue searchEtudiant.jsp pour la recherche d'un étudiant
	 * 
	 * @return
	 */
	@GetMapping("getFormLireEtudiant")
	public String getFormLireEtudiant() {
		return "searchEtudiant";
	}

	/**
	 * En saisissant l'id d'un étudiant, la méthode getById() récupère l'étudiant en
	 * question. Si l'étudiant existe, il est affiché dans la vue detailEtudiant.jsp
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@ApiOperation(value = "Lire un �tudiant")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping("/readEtudiant")
	public String readEtudiant(@RequestParam("idEtudiant") int id, Model model) {
		model.addAttribute("etudiant", ietudiantservice.getById(id));
		
		return "detailEtudiant";
	}
	
	/**
	 * redirige vers la vue rechercheModificationEtudiant.jsp pour la modification
	 * d'un étudiant
	 * 
	 * @return
	 */
	@ApiOperation(value = "Recherche un �tudiant pour modifier celui-ci")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping("getFormModifierEtudiant")
	public String getFormModifierEtudiant() {
		return "rechercheModificationEtudiant";
	}

	/**
	 * En saisissant l'id d'un étudiant, la méthode getById() récupère l'étudiant en
	 * question. Si l'étudiant existe, il est affiché dans la vue
	 * modificationEtudiant.jsp
	 * 
	 * @param id
	 * @param model
	 * @return
	 */

	@ApiOperation(value = "Lire un �tudiant modifi�")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping("/readUpdateEtudiant")
	public String readUpdateEtudiant(@RequestParam("idEtudiant") int id, Model model) {
		model.addAttribute("etudiant", ietudiantservice.getById(id));
		return "modificationEtudiant";
	}

	/**
	 * l'utilisateur va pouvoir changer les informations affiché de l'étudiant une
	 * fois envoyées et valides, l'utilisateur sera envoyer vers la vue
	 * listeEtudiants.jsp
	 * 
	 * @param idEtudiant
	 * @param nom
	 * @param prenom
	 * @param telephone
	 * @param adresse
	 * @param mail
	 * @param dateNaissance
	 * @param etudiant
	 * @param modelMap
	 * @return
	 */

	@ApiOperation(value = "Modifier un �tudiant")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping("/udpateEtudiant") // it only support port method
	public String udpateEtudiant(@RequestParam("idEtudiant") int idEtudiant, @RequestParam("nom") String nom,
			@RequestParam("prenom") String prenom, @RequestParam("telephone") int telephone,
			@RequestParam("adresse") String adresse, @RequestParam("mail") String mail,
			@RequestParam("dateNaissance") String dateNaissance, Etudiant etudiant, ModelMap modelMap) {
		etudiant.setId(idEtudiant);
		etudiant.setNom(nom);
		etudiant.setPrenom(prenom);
		etudiant.setAdresse(adresse);
		etudiant.setTelephone(telephone);
		etudiant.setDateNaissance(dateNaissance);
		ietudiantservice.update(etudiant);
		return "redirect:/listeEtudiants";
	}

	/**
	 * redirige vers la vue rechercheModificationEtudiant.jsp pour la suppression
	 * d'un étudiant
	 * 
	 * @return
	 */

	@ApiOperation(value = "Rechercher un �tudiant pour supprimer celui-ci")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping("getFormSupprimerEtudiant")
	public String getFormSupprimerEtudiant() {
		return "suppressionEtudiant";
	}

	/**
	 * En saisissant l'id d'un étudiant, la méthode getById() supprime l'étudiant en
	 * question. Si l'étudiant existe, la vue messageSuppression.jsp est affichée.
	 * 
	 * @param idEtudiant
	 * @param etudiant
	 * @param modelMap
	 * @return
	 */
	@ApiOperation(value = "Supprimer un �tudiant")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping("/deleteEtudiant") // it only support port method
	public String deleteEtudiant(@RequestParam("idEtudiant") int idEtudiant, ModelMap modelMap) {
		ietudiantservice.deleteStudent(ietudiantservice.getById(idEtudiant));
		return "messageSuppression"; // welcome is view name. It will call welcome.jsp
	}
}
