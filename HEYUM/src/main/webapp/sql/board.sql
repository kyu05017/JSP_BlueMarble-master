



create table bluemarble.freeboard( /* freeboard테이블 생성 */

	bno int primary key auto_increment , -- 게시물번호 
    btitle varchar(50) , 				 -- 게시물제목 
    bcontent LONGTEXT ,					 --  게시물내용 
    ac_no int ,							 --  작성자 = member테이블 mno 관계 
    bfile varchar(1000) ,				 --  게시물첨부파일 
    bview int default 0 ,				 --  게시물 조회수 
    bdate datetime default now() ,		 --  게시물 작성일 
    brecommend int,						 --  게시물 추천 수
    bcategory varchar(20),				 --  게시글 카테고리
    bbcategory varchar(20),				 --  게시판 카테고리 
    foreign key( ac_no ) references aacount(ac_no) 
    
    /* 게시물테이블의 작성자mno와 회원테이블의 회원번호mno와 FK-PK 연결 */
    
)