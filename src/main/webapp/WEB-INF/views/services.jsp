<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<div id="root">
	<c:url value="/" var="root" />
</div>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dr Kang's Dental</title>
<link rel="stylesheet"
	href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="<c:url value='/resources/css/font/flaticon.css'/>">
<script type="text/javascript"
	src="<c:url value='/resources/js/jquery.js'/>"></script>


<script type="text/javascript">
	$(window).load(function() {
		$('#myModal').modal('show');
	});
</script>
</head>
<body>

	<%@include file="dynamicheader.jsp"%>


	<div class="container">

	<br />
		<br />
		<br />
		<h1>Our Services</h1>
		<br />

		<!-- Bootstrap FAQ - START -->
		<div class="container">
			<div id="howitworks">


				<!-- Thumbnail  -->
				<div class="row">
					<div class="col-sm-6 col-md-4">
						<div class="thumbnail">
							<center>
								<div class="caption">
									<h3>Teeth Whitening</h3>


									<i class=flaticon-medical style="color: lightblue;"
										data-toggle="modal" data-target="#teethWhitening"></i>
								</div>
							</center>
						</div>
					</div>


					<div class="col-sm-6 col-md-4">
						<div class="thumbnail">
							<center>
								<div class="caption">
									<h3>White Composite Fillings</h3>

									<i class=flaticon-medical-6 style="color: lightblue;"
										data-toggle="modal" data-target="#whiteComposite"></i>

								</div>
							</center>
						</div>
					</div>

					<div class="col-sm-6 col-md-4">
						<div class="thumbnail">
							<center>
								<div class="caption">
									<h3>Implant Restorations</h3>

									<i class=flaticon-medical-16 style="color: lightblue;"
										data-toggle="modal" data-target="#implants"></i>


								</div>
							</center>
						</div>
					</div>
				</div>


			</div>

			<div id="howitworks">


				<!-- Thumbnail  -->
				<div class="row">
					<div class="col-sm-6 col-md-4">
						<div class="thumbnail">
							<center>
								<div class="caption">
									<h3>Crowns</h3>
									<i class=flaticon-icon-107177 style="color: lightblue;"
										data-toggle="modal" data-target="#crowns"></i>

								</div>
							</center>
						</div>
					</div>


					<div class="col-sm-6 col-md-4">
						<div class="thumbnail">
							<center>
								<div class="caption">
									<h3>Bridges</h3>

									<i class=flaticon-icon-45603 style="color: lightblue;"
										data-toggle="modal" data-target="#bridges"></i>

								</div>
							</center>
						</div>
					</div>

					<div class="col-sm-6 col-md-4">
						<div class="thumbnail">
							<center>
								<div class="caption">
									<h3>Dentures</h3>
									<i class=flaticon-icon-84195 style="color: lightblue;"
										data-toggle="modal" data-target="#dentures"></i>

								</div>
							</center>
						</div>
					</div>


				</div>


			</div>

			<div id="howitworks">


				<!-- Thumbnail  -->
				<div class="row">


					.
					<div class="col-sm-6 col-md-2"></div>
					<div class="col-sm-6 col-md-4">
						<div class="thumbnail">
							<center>
								<div class="caption">
									<h3>Periodontal Therapy</h3>

									<i class=flaticon-icon-77126 style="color: lightblue;"
										data-toggle="modal" data-target="#therapy"></i>

								</div>
							</center>
						</div>
					</div>


					<div class="col-sm-6 col-md-4">

						<div class="thumbnail">
							<center>
								<div class="caption">
									<h3>Root Canal Therapy</h3>

									<i class=flaticon-icon-31045 style="color: lightblue;"
										data-toggle="modal" data-target="#rootcanaltherapy"></i>

								</div>
							</center>
						</div>
					</div>
					<div class="col-sm-6 col-md-2"></div>
				</div>


			</div>




		</div>

		<div class="modal fade" id="myModal" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>

						<h4 class="modal-title">
							<p>
								<strong>Most of Major Private Insurance Plans Accepted</strong>
							</p>
						</h4>
					</div>
					<div class="modal-body">
						<p>If you have dental insurance, we file claims as a courtesy
							for our patients.</p>
						<p>
							Payment is due at <u>each appointment</u> as well as fees not
							covered by insurance.
						</p>
						<p>
							We accept <strong><u>cash</u></strong>,<strong><u>
									cheques</u></strong> and most major<strong><u> credit cards</u></strong>.
						</p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-success" data-dismiss="modal">
							<i class="fa fa-thumbs-o-up"></i> Got It
						</button>
					</div>
				</div>

			</div>
		</div>

		<div class="modal fade" id="teethWhitening" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>

						<h4 class="modal-title">
							<p>
								<strong>Teeth Whitening</strong>
							</p>
						</h4>
					</div>
					<div class="modal-body">
						<p>Many patients are interested in getting their teeth
							whitened. I believe it is my duty to inform and educate patients
							how teeth whitening works. Whitening procedure is most effective
							on stains accumulated exogenously, meaning stains from darker
							food, red wine, coffee, tea, tobacco products, etc. The type I
							use in my clinic is peroxide based methods which it literally
							'strips off' dark stain layer from the tooth surface. It is
							extremely important that after the whitening procedures are
							completed, patients must stay away from the aforementioned dark
							staining sources for few days to prevent relapse of stain
							accumulations.</p>
					</div>
					<div class="modal-footer">

						<a href="profile/scheduleappointment" class="btn btn-primary">Schedule
							Appointment</a>
					</div>
				</div>

			</div>
		</div>

		<div class="modal fade" id="crowns" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>

						<h4 class="modal-title">
							<p>
								<strong>Crowns</strong>
							</p>
						</h4>
					</div>
					<div class="modal-body">
						<p>Restorative dentistry is replacing missing tooth structure
							due to fracture or decay with man-made material to replace the
							function, shape and esthetics of the tooth. When there is
							substantial amount of tooth structure missing, filling
							restorations do not provide functional replacement that can last
							long term. When that happens, a cap in a shape of the restoring
							toothis used to be placed on a top of a 1-2 mm shaven reduced
							tooth structure. I prefer to use all ceramic crowns that do not
							contain any metals. I believe that presence of nickel among other
							metal elements in the old crowns do and can affect the health of
							adjacent gingival health. Metalfree margin also will provide
							potentially better esthetics</p>
					</div>
					<div class="modal-footer">

						<a href="profile/scheduleappointment" class="btn btn-primary">Schedule
							Appointment</a>
					</div>
				</div>

			</div>
		</div>

		<div class="modal fade" id="whiteComposite" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>

						<h4 class="modal-title">
							<p>
								<strong>White Composite Fillings</strong>
							</p>
						</h4>
					</div>
					<div class="modal-body">
						<p>At my office, I exclusively use composite materials for
							filling or restorative procedures. I do place patients concerns
							about mercury presence in old amalgam or alloy based metal
							restorative material. However, that is not the main reason I
							choose to use composite material. Composite restoration allows
							active bonding thus seal between the filling material and tooth
							structure. Also, the material is becoming more and more similar
							to natural tooth structure mechanically, meaning the hardness,
							and 'stretchability' of the material is getting similar to that
							of natural tooth structure. Active bonding and mechanical
							property matching of composite material in my opinion helps
							prevent further loss of tooth structure moving forward.</p>
					</div>
					<div class="modal-footer">

						<a href="profile/scheduleappointment" class="btn btn-primary">Schedule
							Appointment</a>
					</div>
				</div>

			</div>
		</div>

		<div class="modal fade" id="cosmeticBonding" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>

						<h4 class="modal-title">
							<p>
								<strong>Cosmetic Bonding</strong>
							</p>
						</h4>
					</div>
					<div class="modal-body">
						<p>If you have teeth that have stains that cannot be removed
							by bleaching, and veneers or crowns are not necessary, then
							cosmetic bonding might be a good option for you. Cosmetic bonding
							is an affordable, quick and painless way to repair many cosmetic
							flaws or injuries, including:</p>
						<p>Gaps in teeth</p>
						<p>Spots or stains</p>
						<p>Chipped teeth</p>
						<p>Dental bonding sculpts individual teeth without requiring
							removal of any tooth structure. This technique bonds a composite
							material that looks, acts and feels like the real thing, to an
							existing tooth. This procedure usually doesn't require the use of
							an anesthetic; is relatively quick to apply; gives an immediate
							result and is less expensive than veneers.</p>
					</div>
					<div class="modal-footer">

						<a href="profile/scheduleappointment" class="btn btn-primary">Schedule
							Appointment</a>
					</div>
				</div>

			</div>
		</div>

		<div class="modal fade" id="inlays" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>

						<h4 class="modal-title">
							<p>
								<strong>Inlays and Onlays</strong>
							</p>
						</h4>
					</div>
					<div class="modal-body">
						<p>When more than a third of a tooth's biting surface is
							damaged, a dentist will often use an inlay or onlay. Large
							fillings on back teeth often require a stronger and properly
							contoured restoration, such as porcelain inlays or onlays. They
							are created at an off-site dental lab and are bonded into place
							using a strong resin. Inlays and onlays protect teeth similarly
							as crowns, but conserve more natural tooth structure and are a
							better match to your tooth's natural color.</p>
					</div>
					<div class="modal-footer">

						<a href="profile/scheduleappointment" class="btn btn-primary">Schedule
							Appointment</a>
					</div>
				</div>

			</div>
		</div>

		<div class="modal fade" id="bridges" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>

						<h4 class="modal-title">
							<p>
								<strong>Bridges</strong>
							</p>
						</h4>
					</div>
					<div class="modal-body">
						<p>Bridges is an old form of prosthesis designed to replace
							missing teeth. Three or more crowns are made and fused together.
							The end crowns will be attached to existing teeth adjacent to
							missing tooth/teeth area and middle fused crowns effectively
							replace missing teeth by fill in the empty spaces. I recommend
							bridge restorations to patients under specific conditions. There
							are cases where patients cannot have implant surgeries. In those
							specific cases, I recommend non-surgical procedures such as
							bridges or dentures. One biggest drawback of bridges is even if
							teeth next to empty area are health, they needed to be shaved
							down to be fitted to have bridge fixed on them. Once a bridge is
							placed, all teeth involved are marries to each other and even if
							one goes bad, entire bridge needs to be removed and replaced
							after the problem is addressed. Therefore, recommendation of
							bridge placement all factors are considered to make sure the
							bridge is the right treatment option.</p>
					</div>
					<div class="modal-footer">

						<a href="profile/scheduleappointment" class="btn btn-primary">Schedule
							Appointment</a>
					</div>
				</div>

			</div>
		</div>

		<div class="modal fade" id="dentures" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>

						<h4 class="modal-title">
							<p>
								<strong>Dentures</strong>
							</p>
						</h4>
					</div>
					<div class="modal-body">
						<p>Denture treatment is a non-invasive form of replacing
							missing teeth. Prosthetic teeth are fixed to polymer plate. Plate
							is 'clipped' or attached to combinations of remaining existing
							teeth and gum tissue contour to provide stability. Patients need
							to be educated that the denture is not exact or even closer
							replacement of the original teeth. Functionally and
							comfortability, I always inform patients to expect about 25-30%
							of the original teeth. Denture use will require most patients to
							're-learn' how to chew in order to be effective. Upsides to
							denture are: a) non-invasive/non-surgical, b)provides good
							esthetics, and c) lower costs. In addition to that, denture is a
							great prosthetic options for patients to transition during the
							period between extraction of ailing teeth to replacing them in
							implant forms. My approach is to make sure my patients get fully
							informed and educated on dentures before they make their
							decisions to have dentures made for them.</p>
					</div>
					<div class="modal-footer">

						<a href="profile/scheduleappointment" class="btn btn-primary">Schedule
							Appointment</a>
					</div>
				</div>

			</div>
		</div>

		<div class="modal fade" id="implants" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>

						<h4 class="modal-title">
							<p>
								<strong>Implant Restorations</strong>
							</p>
						</h4>
					</div>
					<div class="modal-body">
						<p>In my opinion, implant is the closes replacement of missing
							teeth in functional sense. Implant system consists of a metal
							component, that is embedded within the bone, and a crown, that
							actually replaces missing tooth by fixed to the embedded metal
							component. Recent advancements in understanding of bone
							integration onto the implant surface, development of further
							advanced biocompatible materials as implant materials and
							surgical technique made implant option more viable. In my office,
							implant placement surgery is done by a surgeon referred by me.
							Once the implant healing and integration to bone is complete,
							implant crown is made and fitted to the implant to complete the
							restoration of missing dentition. The entire procedure will take
							between 4-9 months depends on type of procedures needed to place
							implants.</p>
					</div>
					<div class="modal-footer">

						<a href="profile/scheduleappointment" class="btn btn-primary">Schedule
							Appointment</a>
					</div>
				</div>

			</div>
		</div>

		<div class="modal fade" id="therapy" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>

						<h4 class="modal-title">
							<p>
								<strong>Periodontal Therapy</strong>
							</p>
						</h4>
					</div>
					<div class="modal-body">
						<p>In most cases, patients that are forced to have full
							dentures suffer from chronic severe periodontal disease.
							Periodontal or gum disease, also commonly known, affect all teeth
							by weakening there bone foundation. This happens because chronic
							low grade gum infection, which causes gum tissue to swell red,
							extremely sensitive, painful and bleeding easily, will ultimately
							causes underlying bone structure to degenerate or 'melt' away. If
							this periodontal condition is not diagnosed in timely manner and
							treated appropriately, eventually, teeth will become mobile and
							non-functional. Treatments of gum diseases are mainly classified
							as non-surgical therapy, also known as deep scaling and surgical
							therapy, including laser based surgery. When a patient with
							periodontal disease presents at my office, I make diagnosis and
							based on that, 3 major treatment recommendations are made. First
							is provide patients with non-surgical deep scaling followed by 3
							month cleaning also known as periodontal maintenance. Second is
							refer to a periodontist, a specialist that can evaluate and
							provide surgical treatments. Third is when the teeth are
							determined non-salvageable, extractions are performed either at
							my office or by a specialists depends on the type and the level
							of complications involved with particular extractions. In my
							opinion, successful outcome of periodontal treatments depends on
							good patient education, good and timely clinical diagnosis and
							treatments and patient commitments on diligent home care and 3
							month periodontal maintenance. I always tell my patients deep
							scaling is only a small portion of the overall treatment scheme
							of periodontal disease, persistent home care and perio
							maintenance is the vital key to successful outcome.</p>
					</div>
					<div class="modal-footer">

						<a href="profile/scheduleappointment" class="btn btn-primary">Schedule
							Appointment</a>
					</div>
				</div>

			</div>
		</div>

		<div class="modal fade" id="rootcanaltherapy" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>

						<h4 class="modal-title">
							<p>
								<strong>Root Canal Therapy</strong>
							</p>
						</h4>
					</div>
					<div class="modal-body">
						<p>Large decay of tooth structure that is not treated in time
							may advance into the nerve chamber inthe center of tooth
							structure. This cause infection of the nerve that causes severe
							pain that can also results in acute swelling. Root canal is
							basically removal of infected nerve within the tooth structure,
							clean and disinfect the canal space and fill it with polymeric
							materials to seal the canalspace. At my office, the assessment
							for the root canal therapy is made to determine, 1) if root canal
							is a right choice of treatment, 2) if it is, assess if the
							procedures will require special equipments such as high power
							microscopes. If the procedure will require such equipments, the
							case is referred to a root canal specialist. 3) if the case does
							not require such equipments, the procedure is rendered at the
							office by Dr Kang.</p>


					</div>
					<div class="modal-footer">

						<a href="profile/scheduleappointment" class="btn btn-primary">Schedule
							Appointment</a>
					</div>
				</div>

			</div>
		</div>



	</div>


	<%@include file="footer.jsp"%>

</body>
</html>