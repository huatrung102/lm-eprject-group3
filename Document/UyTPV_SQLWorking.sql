USE [Set05]
GO

INSERT INTO [dbo].[Categories]
           ([Cat_Id]
           ,[Cat_Name]
           ,[Cat_isDelete]
           ,[Cat_Description])
     VALUES
           (1,N'Novel', 0, N'Novel'),
		   (2,N'Economic', 0, N'Economic'),
		   (3,N'Specialization', 0, N'Specialization'),
		   (4,N'Life Style', 0, N'Life Style'),
		   (5,N'Schoolbook', 0, N'Schoolbook'),
		   (6,N'Dictionary', 0, N'Dictionary'),
		   (7,N'Culture and Art', 0, N'Culture and Art'),
		   (8,N'Manga - Comic', 0, N'Manga - Comic'),
		   (9,N'Travel', 0, N'Travel'),
		   (10,N'Magazine', 0, N'Magazine'),
		   (0,N'Khác', 0, N'Thể loại Khác')     
GO

USE [Set05]
GO
CREATE PROCEDURE [getListCategory]
AS
	BEGIN
		SELECT Cat_Name
		  FROM [Categories]
	END
GO

