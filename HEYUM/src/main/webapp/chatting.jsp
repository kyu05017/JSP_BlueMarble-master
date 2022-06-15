<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<div class="row mt-5">
		<div id="chattingbox" class="chattingbox col-md-10 offset-md-1 p-3">
			22건전한 대화를 해주시기 바랍니다.<br>
		</div>
		</div>
		<div class="row mt-2 g-0">
		<div class="col-md-2">
			<div class="dropdown">
				<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
					이모티콘
				</button>
				<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
					<li>
					<button onclick="emoji(1)">&#128151</button>
					<button onclick="emoji(1)">&#128151</button>
					<button onclick="emoji(1)">&#128151</button>
					<button onclick="emoji(1)">&#128151</button>
					<button onclick="emoji(1)">&#128151</button>
					<button onclick="emoji(1)">&#128151</button>
					</li>
					<li onclick="emoji(2)">&#128163</li>
					<li onclick="emoji(3)">&#128165</li>
					<li onclick="emoji(4)">&#128169</li>
					<li onclick="emoji(5)">&#128293</li>
					<li onclick="emoji(6)">&#128514</li>
				</ul>
			</div>
		</div>
		<div class="col-md-8">
			<input type="text" class="form-control" id="msginput">
		</div>
		<div class="col-md-1">
			<button class="form-control" type="button" onclick="sendmsg()">전송</button>
		</div>
	</div>
	
</body>
</html>