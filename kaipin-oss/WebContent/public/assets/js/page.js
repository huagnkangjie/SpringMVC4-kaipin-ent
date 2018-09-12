;$(
		function() {

			// 第一页
			$('.firstPage').on(
					'click',
					function(e) {
						e.preventDefault();

						var refObject = $(e.target.parentNode.parentNode).attr(
								"refObject"); // ul的refObject

						loadPages(1, refObject);

					});

			// 上一页
			$('.prevPage').on(
					'click',
					function(e) {
						e.preventDefault();
						var refObject = $(e.target.parentNode.parentNode).attr(
								"refObject"); // ul的refObject
						var currentPage = parseInt($(
								e.target.parentNode.parentNode).attr(
								"currentPage"));
						var toPage = currentPage - 1;
						loadPages(toPage, refObject);
					});

			// 下一页

			$('.nextPage').on(
					'click',
					function(e) {
						e.preventDefault();
						var refObject = $(e.target.parentNode.parentNode).attr(
								"refObject"); // ul的refObject
						var currentPage = parseInt($(
								e.target.parentNode.parentNode).attr(
								"currentPage"));
						
						//alert(currentPage);
						
						var toPage = currentPage + 1;
						
				//		alert(toPage);
						
						loadPages(toPage, refObject);
					});

			// 最后
			$('.lastPage')
					.on(
							'click',
							function(e) {
								e.preventDefault();
								var refObject = $(
										e.target.parentNode.parentNode).attr(
										"refObject"); // ul的refObject
								var totalPages = parseInt($(
										"#totalPagesOf" + refObject).html());
								loadPages(totalPages, refObject);
							});

			// 刷新
			$('.refreshCurrentPage').on(
					'click',
					function(e) {
						e.preventDefault();
						var refObject = $(e.target.parentNode.parentNode).attr(
								"refObject"); // ul的refObject
						var currentPage = parseInt($(
								e.target.parentNode.parentNode).attr(
								"currentPage"));
						loadPages(currentPage, refObject);
					});

			$('.pagesizeOfRefObject').on('change', function(e) {
			//	e.preventDefault();
				var refObject = $(e.target).attr("id").substr(10);

			//	var p1=$(this).children('option:selected').val();//这就是selected的值 
		 
				
				 loadPages(1, refObject);

			});

			$('.toPageOfRefObject').on(
					'keypress',
					function(e) {

						if (e.keyCode == 13) {
							var refObject = $(
									e.target.parentNode.parentNode.parentNode)
									.attr("refObject"); // ul的refObject

							var toPage = $("#toPageOf" + refObject).val();
							
		
							
							
							
							loadPages(toPage, refObject);
						}

					});
			
			
			
			
			function loadPages(toPage, refObject) {

				var totalPages = parseInt($("#totalPagesOf" + refObject).html());
				
				
			 
				var toItems = parseInt($("#totalItemsOf" + refObject).html());
				if (!/^[0-9]{1,}$/.test(toPage) || toPage < 1) {
					toPage = 1;
				}
				if (toPage > totalPages) {
					toPage = totalPages;
				}
				//alert("toPage->"+toPage)
				
				$("#toPageOf" + refObject).val(toPage);
				var refObjectLower = refObject.substr(0, 1).toLowerCase()
						+ refObject.substr(1, refObject.length - 1);
				var pagesize = $("#pagesizeOf" + refObject).val();
				
				if (pagesize == null) {
					pagesize = 20;
				} else {
					pagesize = parseInt($("#pagesizeOf" + refObject).val());
				}
			 

				var form_search = $('form.form-search');


				if (form_search.length ) {

					$('#pageNo').val(toPage);
					
					//alert(	$('#pageNo').val( ));

					$('#pageSize').val(pagesize);
				form_search.submit();
				}

			}
			
			
			
			
			
			

		})(jQuery);


