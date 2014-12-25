CREATE DATABASE LMS
GO
USE LMS
GO

CREATE TABLE Members(
	Mem_Id char(36) not null PRIMARY KEY DEFAULT newid(),
	Mem_FirstName nvarchar(30),
	Mem_LastName nvarchar(50),
	Mem_Phone varchar(20),
	Mem_Address nvarchar(200),
	Mem_Email varchar(50) not null unique,
	Mem_Status int not null, -- 0:bi xoa,1 hoat dong,2:bi khoa
	Mem_CreateDate datetime not null default getdate()
)
GO
CREATE TABLE Roles(
	Role_Id varchar(15) not null PRIMARY KEY,
	Role_Name nvarchar(20) not null,
	Role_Description nvarchar(100)
)
GO
CREATE TABLE Staffs(
	Staff_Id char(36) not null PRIMARY KEY DEFAULT newid(),
	Staff_FirstName nvarchar(30),
	Staff_LastName nvarchar(50),
	Staff_Login  varchar(25) not null unique,
	Staff_Password varchar(50),
	Staff_Phone varchar(20),
	Role_Id varchar(15) FOREIGN KEY REFERENCES Roles(Role_Id),
	Staff_Address nvarchar(200),
	Staff_Email varchar(50) not null unique,
	Staff_Status int not null,	-- 0:bi xoa,1 hoat dong,2:bi khoa
	Staff_CreateDate datetime not null default getdate()
)
GO
CREATE TABLE Categories(
	Cat_Id char(36) not null PRIMARY KEY DEFAULT newid(),
	Cat_Name nvarchar(30)
)
GO
CREATE TABLE Languages(
	Lang_Id varchar(7) not null PRIMARY KEY ,
	Lang_Name nvarchar(30)
)
GO
CREATE TABLE Books(
	Book_ISBN varchar(13) not null  PRIMARY KEY,
	Book_Title nvarchar(50) not null,
	Book_Publisher nvarchar(50) not null,
	Book_Author nvarchar(50) not null,
	--Book_Quanlity int not null, no need
	Book_Price money not null,
	Cat_Id char(36) FOREIGN KEY REFERENCES Categories(Cat_Id),
	Lang_Id  varchar(7)  FOREIGN KEY REFERENCES Languages(Lang_Id),
	Book_ImageFile varchar(255) not null default '/img/nocover.png',
	Book_CreateDate datetime not null default getdate()
)
GO
CREATE TABLE Copies(
	Cop_Id	varchar(16) not null PRIMARY KEY,--book ISBN + 3 number char from 001 - 999 (use function sql to get the numbers)
	Book_ISBN varchar(13) FOREIGN KEY REFERENCES Books(Book_ISBN),
	Cop_Status bit not null,
	Cop_isDeleted bit not null
)
GO
CREATE TABLE Orders(
	Order_Id char(36) not null PRIMARY KEY DEFAULT newid(),
	Mem_Id char(36) FOREIGN KEY REFERENCES Members(Mem_Id),
	Order_CreateDate datetime not null default getdate(),
	Order_DueDate datetime not null,
	Order_isDeleted bit
)
GO
CREATE TABLE OrderDetails(
	OrderDetail_Id char(36) not null PRIMARY KEY DEFAULT newid(),
	Order_Id char(36) FOREIGN KEY REFERENCES Orders(Order_Id),
	Cop_Id varchar(16) FOREIGN KEY REFERENCES Copies(Cop_Id),
	OrderDetail_Status bit,
	OrderDetail_ReturnDate datetime,
	OrderDetail_isDeleted bit
)
GO
CREATE TABLE Fines(
	Fine_Id char(36) not null PRIMARY KEY DEFAULT newid(),
	OrderDetail_Id char(36) FOREIGN KEY REFERENCES OrderDetails(OrderDetail_Id),
	Fine_Amount money not null,
	Fine_CreateDate datetime not null default getdate(),
	Fine_PaidDate datetime,
	Fine_Status bit
)

