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
<title>Insert title here</title>
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
				</div>


			</div>

			<div id="howitworks">


				<!-- Thumbnail  -->
				<div class="row">
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

					<div class="col-sm-6 col-md-4">
						<div class="thumbnail">
							<center>
								<div class="caption">
									<h3>Dental Implants</h3>

									<i class=flaticon-medical-16 style="color: lightblue;"
										data-toggle="modal" data-target="#implants"></i>


								</div>
							</center>
						</div>
					</div>
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
						<p>Do you want a beautiful smile? Whitening your teeth is one
							of the easiest things you can do to improve your smile and boost
							your self-confidence. Imagine, a single whitening procedure can
							counteract years of exposure to coffee, tea, red wine, cola and
							smoking, giving you the smile you've always dreamed of!</p>
					</div>
					<div class="modal-footer">

						<a href="events.jsp" class="btn btn-primary">Schedule
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
						<p>Teeth are often restored using silver or porcelain
							fillings. However, when too much of a tooth's structure is
							removed to support a filling, a crown or "cap" may be needed. A
							crown may be needed to:</p>
						<p>Restore a tooth when it is unable to support a large
							filling</p>
						<p>Attach bridges</p>
						<p>Protect a weak tooth from fracturing or restore fractured
							teeth</p>
						<p>Cover badly shaped or discolored teeth</p>
						<p>Cover dental implants</p>
						<p>A crown essentially covers a tooth to restore it to its
							natural shape and size. This permanent covering fits over your
							original tooth to strengthen or improve the appearance of the
							tooth. Fitting a crown generally requires at least two visits to
							the dentist's office.</p>
					</div>
					<div class="modal-footer">

						<a href="events.jsp" class="btn btn-primary">Schedule
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
						<p>Until recently, dentists used a silver and mercury amalgam
							to fill and seal cavities. Because a larger amount of the
							original tooth must be removed, these types of fillings often
							weaken teeth. Recent innovations now allow us to replace old
							silver fillings with composite or porcelain fillings that are
							stronger, safer and more natural looking. Say goodbye to "metal
							mouth" and hello to a beautiful new smile.</p>
					</div>
					<div class="modal-footer">

						<a href="events.jsp" class="btn btn-primary">Schedule
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

						<a href="events.jsp" class="btn btn-primary">Schedule
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

						<a href="events.jsp" class="btn btn-primary">Schedule
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
						<p>Healthy and strong teeth support and maintain your jaw
							alignment. That is why when you lose one or more teeth, your
							remaining teeth may shift, and the ability to chew and speak may
							be affected. A bridge replaces missing teeth with artificial
							teeth, eliminating the gap where those teeth had been. The bridge
							is bonded onto surrounding teeth for support. Regardless of the
							type of bridge recommended, its success depends on keeping the
							surrounding teeth healthy and strong.</p>
					</div>
					<div class="modal-footer">

						<a href="events.jsp" class="btn btn-primary">Schedule
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
						<p>Missing teeth can create a host of aesthetic and functional
							problems including sagging facial muscles, inability to eat
							certain foods and speak. Dentures may be your best solution if
							you've lost all your natural teeth due to periodontal disease,
							tooth decay or injury.</p>
						<p>Fitting a patient for dentures does not have to be a
							traumatic experience. During the preliminary visit, the remaining
							teeth are removed and a model is made of the patient's jaw. An
							immediate denture may be fitted so the patient does not have to
							be without teeth while the mouth tissue heals. Once the mouth
							tissue is completely healed, the patient is fitted with their
							custom dentures.</p>
					</div>
					<div class="modal-footer">

						<a href="events.jsp" class="btn btn-primary">Schedule
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
								<strong>Dental Implants</strong>
							</p>
						</h4>
					</div>
					<div class="modal-body">
						<p>When you lose a tooth, a dental implant may be needed to
							replace the tooth root and crown. Dental implants are simply
							"anchors" that permanently support replacement teeth. They are
							secure and durable and can be cleaned and cared for much like
							your natural teeth.</p>
						<p>The procedure requires a titanium root be fitted into your
							jaw to replace the lost tooth's root. Once the implant is
							anchored into the bone, the bone around the implant requires six
							weeks to six months of healing. Once the bone has healed, a
							support post and replacement tooth is anchored onto the implant.</p>
					</div>
					<div class="modal-footer">

						<a href="events.jsp" class="btn btn-primary">Schedule
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
						<p>There have been major advances in non-surgical periodontal
							treatment for chronic periodontal disease. Our state-of-the-art
							ultrasonic scalers have been designed by a periodontist for
							maximum effectiveness on root surfaces. Antibiotic gels, which
							are placed below the gum line, reduce the need for periodontal
							surgery. And the latest innovation, a dental endoscope (camera)
							which can be placed below the gum line, further reduces the need
							for surgical treatment of periodontal disease.</p>
						<p>We take every opportunity to help you control your
							periodontal disease problem non-surgically and then reevaluate to
							determine the effectiveness of the treatment. If surgical
							treatment is required, the treatments maximize your comfort and
							your ability to regrow lost periodontal support.</p>
					</div>
					<div class="modal-footer">

						<a href="events.jsp" class="btn btn-primary">Schedule
							Appointment</a>
					</div>
				</div>

			</div>
		</div>



	</div>


	<%@include file="footer.jsp"%>

</body>
</html>