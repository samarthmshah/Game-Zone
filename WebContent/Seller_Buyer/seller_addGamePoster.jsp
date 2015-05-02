<div class="product-preview-popup">
	<div class="row" style="overflow: hidden;">

		<div class="col-md-4"></div>
		<div class="col-md-4">
			<form action="<%=request.getContextPath()%>/FileUploadController"
				method="post" enctype="multipart/form-data">
				<div class="form-group">
					<div class="product-name">
					<h2>
						<label class="form-label" for="game_poster_path">
							Image Upload
						</label>
					</h2> 
					</div>
					
					
					<input type="file" 
					class="form-control form-control-line" name="img" size="50"
						id="game_poster_path" required="required">
				</div>
				
				<div class="line-divider"></div>
				<button type="submit" class="btn-buy">
					<span class="icon typcn typcn-upload"></span><span
						class="text">UPLOAD IMAGE</span>
				</button>
			</form>
		</div>
		<div class="col-md-4"></div>
	</div>
</div>