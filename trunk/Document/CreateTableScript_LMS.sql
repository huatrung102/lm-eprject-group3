CREATE DATABASE LMS
GO
USE LMS
GO

CREATE TABLE Members(
	Mem_Id char(36) not null PRIMARY KEY DEFAULT newid(),
	Mem_FirstName nvarchar(30) not null,
	Mem_LastName nvarchar(50) not null,
	Mem_Phone varchar(20),
	Mem_Address nvarchar(200),
	Mem_Email varchar(50) not null unique,
	Mem_Status bit not null default 1, -- 0:bi xoa,1 hoat dong,2:bi khoa
	Mem_CreateDate datetime not null default getdate(),
	Mem_isDelete bit not null default 0,
	Mem_ImageFile varchar(255) not null default '/imgMem/noavatar.png',
)
GO

CREATE TABLE Staffs(
	Staff_Id char(36) not null PRIMARY KEY DEFAULT newid(),
	Staff_FirstName nvarchar(30),
	Staff_LastName nvarchar(50),
	Staff_Login  varchar(25) not null unique,
	Staff_Password varchar(50) not null,
	Staff_Phone varchar(20),
	Staff_Role varchar(15) not null,
	Staff_Address nvarchar(200),
	Staff_Email varchar(50) not null unique,
	Staff_Status int not null default 1,	-- 0:bi xoa,1 hoat dong,2:bi khoa
	Staff_CreateDate datetime not null default getdate(),
	Staff_isDelete bit not null default 0,
	Staff_ImageFile varchar(255) not null default '/imgStaff/noavatar.png',
)
GO
CREATE TABLE Categories(
	Cat_Id char(36) not null PRIMARY KEY DEFAULT newid(),
	Cat_Name nvarchar(30) not null unique,
	Cat_Description nvarchar(100) 
)	
GO

CREATE TABLE Books(
	Book_ISBN varchar(13) not null  PRIMARY KEY,
	Book_Title nvarchar(50) not null,
	Book_Publisher nvarchar(50), --not null,
	Book_Author nvarchar(50),-- not null,	
	Book_Price money not null,
	Book_Content nvarchar(1000) not null default '',
	Cat_Id char(36) FOREIGN KEY REFERENCES Categories(Cat_Id),
	Book_Language  varchar(7)  not null,
	Book_ImageFile varchar(255) not null default '/imgBook/nocover.png',
	Book_CreateDate datetime not null default getdate()
)
GO
CREATE TABLE Copies(
	Cop_Id	char(36) not null PRIMARY KEY DEFAULT newid(),--book ISBN + 3 number char from 001 - 999 (use function sql to get the numbers)
	Book_ISBN varchar(13) FOREIGN KEY REFERENCES Books(Book_ISBN),
	Cop_Status bit not null default 1,
	Cop_isDeleted bit not null default 0
)
GO
CREATE TABLE IRBooks(
	IRBook_Id char(36) not null PRIMARY KEY DEFAULT newid(),
	Mem_Id char(36) FOREIGN KEY REFERENCES Members(Mem_Id),
	IRBook_CreateDate datetime not null default getdate(),
	IRBook_DueDate datetime not null,
	IRBook_Description nvarchar(100)
)
GO
CREATE TABLE IRBookDetails(
	IRBookDetail_Id char(36) not null PRIMARY KEY DEFAULT newid(),
	IRBook_Id char(36) FOREIGN KEY REFERENCES IRBooks(IRBook_Id),
	Cop_Id char(36) FOREIGN KEY REFERENCES Copies(Cop_Id),
	IRBookDetail_Status bit not null default 0,
	IRBookDetail_ReturnDate datetime,
	IRBookDetail_isDeleted bit not null default 0
)
GO
CREATE TABLE Fines(
	Fine_Id char(36) not null PRIMARY KEY DEFAULT newid(),
	IRBookDetail_Id char(36) FOREIGN KEY REFERENCES IRBookDetails(IRBookDetail_Id),
	Fine_Amount money not null,
	Fine_CreateDate datetime not null default getdate(),
	Fine_PaidDate datetime,
	Fine_Status bit not null default 0
)

