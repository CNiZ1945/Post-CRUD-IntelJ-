INSERT INTO article (title, content) VALUES('가가가가', '1111');
INSERT INTO article (title, content) VALUES('나나나나', '2222');
INSERT INTO article (title, content) VALUES('다다다다', '3333');



-- 1. article 테이블 데이터 추가
INSERT INTO article (title, content) VALUES('당신의 인생영화는?', '댓글 go');
INSERT INTO article (title, content) VALUES('당신의 소울푸드', '댓글 환영');
INSERT INTO article (title, content) VALUES('취미 댓글 환영', '댓글 댓글 댓글');

-- 2. 4번 게시글의 댓글 추가
INSERT INTO comment (article_id, nickname, body) VALUES(4, 'google', '굿윌 헌팅');
INSERT INTO comment (article_id, nickname, body) VALUES(4, 'naver', '듄');
INSERT INTO comment (article_id, nickname, body) VALUES(4, 'daum', '아이언맨이 짱임');

-- 3. 5번 게시글의 댓글 추가
INSERT INTO comment (article_id, nickname, body) VALUES(5, 'google', '치맥');
INSERT INTO comment (article_id, nickname, body) VALUES(5, 'naver', '역시 라면');
INSERT INTO comment (article_id, nickname, body) VALUES(5, 'daum', '빵 맛');

-- 3. 6번 게시글의 댓글 추가
INSERT INTO comment (article_id, nickname, body) VALUES(6,'Pack' , '조깅');
INSERT INTO comment (article_id, nickname, body) VALUES(6,'Kim' , '유튜브 시청');
INSERT INTO comment (article_id, nickname, body) VALUES(6,'Choi' , '독서');

drop table article;