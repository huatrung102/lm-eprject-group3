USE [LMS]
GO
/****** Object:  Table [dbo].[Books]    Script Date: 1/20/2015 11:15:41 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Books](
	[Book_ISBN] [varchar](13) NOT NULL,
	[Book_Title] [nvarchar](50) NOT NULL,
	[Book_Publisher] [nvarchar](50) NULL,
	[Book_Author] [nvarchar](50) NULL,
	[Book_Price] [money] NOT NULL,
	[Book_Content] [nvarchar](1000) NOT NULL,
	[Cat_Id] [char](36) NULL,
	[Book_Language] [varchar](20) NOT NULL,
	[Book_ImageFile] [varchar](255) NOT NULL,
	[Book_CreateDate] [datetime] NOT NULL,
	[Book_isDeleted] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Book_ISBN] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Categories]    Script Date: 1/20/2015 11:15:41 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Categories](
	[Cat_Id] [char](36) NOT NULL,
	[Cat_Name] [nvarchar](30) NOT NULL,
	[Cat_isDelete] [bit] NOT NULL,
	[Cat_Description] [nvarchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[Cat_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Copies]    Script Date: 1/20/2015 11:15:41 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Copies](
	[Cop_Id] [char](36) NOT NULL,
	[Cop_No] [char](15) NOT NULL,
	[Book_ISBN] [varchar](13) NULL,
	[Cop_Status] [bit] NOT NULL,
	[Cop_isDeleted] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Cop_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[Books] ([Book_ISBN], [Book_Title], [Book_Publisher], [Book_Author], [Book_Price], [Book_Content], [Cat_Id], [Book_Language], [Book_ImageFile], [Book_CreateDate], [Book_isDeleted]) VALUES (N'9780316373807', N'See How Small', N'Little Brown and Company', N'Scott Blackwood', 11.9900, N'A riveting novel about the aftermath of a brutal murder of three teenage girls written in incantatory prose thats as fine as any being written by an American author today. 
', N'40929920-5B3D-4071-BE2B-5FAC464A48CB', N'English', N'imgBook/20150120105738_seehowsmallanovel.jpg', CAST(0x0000A42600B4A0AE AS DateTime), 0)
INSERT [dbo].[Books] ([Book_ISBN], [Book_Title], [Book_Publisher], [Book_Author], [Book_Price], [Book_Content], [Cat_Id], [Book_Language], [Book_ImageFile], [Book_CreateDate], [Book_isDeleted]) VALUES (N'9780345539816', N'Golden Son ', N'Del Rey ', N'Pierce Brown ', 19.0000, N'With shades of The Hunger Games Enders Game and Game of Thrones debut author Pierce Browns genre-defying epic Red Rising hit the ground running and wasted no time becoming a sensation.
', N'8CD5271F-CAC4-4D23-A199-4AEF5A47D113', N'Russian', N'imgBook/20150120110654_GoldenSon.jpg', CAST(0x0000A42600B72C15 AS DateTime), 0)
INSERT [dbo].[Books] ([Book_ISBN], [Book_Title], [Book_Publisher], [Book_Author], [Book_Price], [Book_Content], [Cat_Id], [Book_Language], [Book_ImageFile], [Book_CreateDate], [Book_isDeleted]) VALUES (N'9780544309876', N'Black River ', N'Houghton Mifflin Harcourt ', N'S. M. Hulse ', 15.9900, N'A tense Western and an assured debut Black River tells the story of a man marked by a prison riot as he returns to the town and the convict who shaped him.
', N'4FE6CDBA-49A4-4A6D-B719-7DAAE3599304', N'French', N'imgBook/20150120110305_BlackRiver.jpg', CAST(0x0000A42600B61F65 AS DateTime), 0)
INSERT [dbo].[Books] ([Book_ISBN], [Book_Title], [Book_Publisher], [Book_Author], [Book_Price], [Book_Content], [Cat_Id], [Book_Language], [Book_ImageFile], [Book_CreateDate], [Book_isDeleted]) VALUES (N'9780670785957', N'West of Sunset', N'Viking Adult', N'Stewart ONan', 11.9900, N'A rich sometimes heartbreaking (Dennis Lehane novel of F. Scott Fitzgeralds last years in Hollywood
', N'4FE6CDBA-49A4-4A6D-B719-7DAAE3599304', N'English', N'imgBook/20150120105942_WestofSunset.jpg', CAST(0x0000A42600B53209 AS DateTime), 0)
INSERT [dbo].[Books] ([Book_ISBN], [Book_Title], [Book_Publisher], [Book_Author], [Book_Price], [Book_Content], [Cat_Id], [Book_Language], [Book_ImageFile], [Book_CreateDate], [Book_isDeleted]) VALUES (N'9780770433246', N'It Was Me All Along', N'Clarkson Potter', N'Andie Mitchell', 14.4000, N'A heartbreakingly honest endearing memoir of incredible weight loss by a young food blogger who battles body image issues and overcomes food addiction to find self-acceptance.
', N'8C3AB2C7-5DE4-458E-83DD-A721792D5429', N'German', N'imgBook/20150120110551_ItWasMeAllAlong.jpg', CAST(0x0000A42600B6E285 AS DateTime), 0)
INSERT [dbo].[Books] ([Book_ISBN], [Book_Title], [Book_Publisher], [Book_Author], [Book_Price], [Book_Content], [Cat_Id], [Book_Language], [Book_ImageFile], [Book_CreateDate], [Book_isDeleted]) VALUES (N'9780871407900', N'Sweetland A Novel', N'Liveright', N'Michael Crummey', 17.9700, N'The epic tale of an endangered Newfoundland community and the struggles of one man determined to resist its extinction
', N'4FE6CDBA-49A4-4A6D-B719-7DAAE3599304', N'German', N'imgBook/20150120110128_SweetlandANovel.jpg', CAST(0x0000A42600B5ADDF AS DateTime), 0)
INSERT [dbo].[Books] ([Book_ISBN], [Book_Title], [Book_Publisher], [Book_Author], [Book_Price], [Book_Content], [Cat_Id], [Book_Language], [Book_ImageFile], [Book_CreateDate], [Book_isDeleted]) VALUES (N'9781439172568', N'The First Bad Man ', N'Scribner ', N'Miranda July ', 15.0000, N'From the acclaimed filmmaker artist and bestselling author of No One Belongs Here More Than You a spectacular debut novel that is so heartbreaking so dirty so tender so funny—so Miranda July—that readers will be blown away.
', N'7ED34419-10EE-40FC-81AD-B35108D3B506', N'English', N'imgBook/20150120110448_TheFirstBadMan.jpg', CAST(0x0000A42600B698CB AS DateTime), 0)
INSERT [dbo].[Books] ([Book_ISBN], [Book_Title], [Book_Publisher], [Book_Author], [Book_Price], [Book_Content], [Cat_Id], [Book_Language], [Book_ImageFile], [Book_CreateDate], [Book_isDeleted]) VALUES (N'9781455551927', N'The Secret Wisdom of the Earth', N'Grand Central Publishing', N'Christopher Scotton', 11.0000, N'After witnessing the death of his younger brother in a terrible home accident 14-year-old Kevin and his grieving mother are sent for the summer to live with Kevins grandfather', N'BBACEBF6-096B-4B9A-823A-941BB5F17AA9', N'Vietnam', N'imgBook/20150120110741_TheSecretWisdomoftheEarth.jpg', CAST(0x0000A42600B76300 AS DateTime), 0)
INSERT [dbo].[Books] ([Book_ISBN], [Book_Title], [Book_Publisher], [Book_Author], [Book_Price], [Book_Content], [Cat_Id], [Book_Language], [Book_ImageFile], [Book_CreateDate], [Book_isDeleted]) VALUES (N'9781484205396', N'Beginning Xcode', N'Apress', N'Matthew Knott', 11.0000, N'Beginning Xcode Swift Edition will not only get you up and running with Apples latest version of Xcode but it also shows you how to use Swift in Xcode and includes a variety of projects to build.
', N'EA3D573E-EBD6-4DBE-A5CB-36DAB14F9EF9', N'English', N'imgBook/20150120110825_beginningxcode.jpg', CAST(0x0000A42600B796F5 AS DateTime), 0)
INSERT [dbo].[Books] ([Book_ISBN], [Book_Title], [Book_Publisher], [Book_Author], [Book_Price], [Book_Content], [Cat_Id], [Book_Language], [Book_ImageFile], [Book_CreateDate], [Book_isDeleted]) VALUES (N'9781594205866', N'Leaving Before the Rains Come', N'Penguin Press HC The', N'Alexandra Fuller', 16.2000, N'...one of the gutsiest memoirs Ive ever read. And the writing--oh my god the writing. --Entertainment Weekly', N'4FE6CDBA-49A4-4A6D-B719-7DAAE3599304', N'French', N'imgBook/20150120110217_LeavingBeforetheRainsCome.jpg', CAST(0x0000A42600B5E762 AS DateTime), 0)
INSERT [dbo].[Books] ([Book_ISBN], [Book_Title], [Book_Publisher], [Book_Author], [Book_Price], [Book_Content], [Cat_Id], [Book_Language], [Book_ImageFile], [Book_CreateDate], [Book_isDeleted]) VALUES (N'9781594633669', N'The Girl on the Train', N'Riverhead Hardcover', N'Riverhead Hardcover', 16.1700, N'Gone Girl fans will devour this psychological thriller.—People 
', N'4FE6CDBA-49A4-4A6D-B719-7DAAE3599304', N'Spanish', N'imgBook/20150120110406_TheGirlontheTrain.jpg', CAST(0x0000A42600B666F2 AS DateTime), 0)
INSERT [dbo].[Categories] ([Cat_Id], [Cat_Name], [Cat_isDelete], [Cat_Description]) VALUES (N'40929920-5B3D-4071-BE2B-5FAC464A48CB', N'Education', 0, N'Education')
INSERT [dbo].[Categories] ([Cat_Id], [Cat_Name], [Cat_isDelete], [Cat_Description]) VALUES (N'4FE6CDBA-49A4-4A6D-B719-7DAAE3599304', N'Novels', 0, N'Novels')
INSERT [dbo].[Categories] ([Cat_Id], [Cat_Name], [Cat_isDelete], [Cat_Description]) VALUES (N'7ED34419-10EE-40FC-81AD-B35108D3B506', N'Comics & Graphic ', 0, N'Comics & Graphic ')
INSERT [dbo].[Categories] ([Cat_Id], [Cat_Name], [Cat_isDelete], [Cat_Description]) VALUES (N'8C3AB2C7-5DE4-458E-83DD-A721792D5429', N'Health, Fitness', 0, N'Health, Fitness')
INSERT [dbo].[Categories] ([Cat_Id], [Cat_Name], [Cat_isDelete], [Cat_Description]) VALUES (N'8CD5271F-CAC4-4D23-A199-4AEF5A47D113', N'Teen & Young ', 0, N'Teen & Young ')
INSERT [dbo].[Categories] ([Cat_Id], [Cat_Name], [Cat_isDelete], [Cat_Description]) VALUES (N'BBACEBF6-096B-4B9A-823A-941BB5F17AA9', N'Travel', 0, N'Travel')
INSERT [dbo].[Categories] ([Cat_Id], [Cat_Name], [Cat_isDelete], [Cat_Description]) VALUES (N'EA164A6E-3939-4D1A-A704-2B1A3B5AB65E', N'Romance', 0, N'Romance')
INSERT [dbo].[Categories] ([Cat_Id], [Cat_Name], [Cat_isDelete], [Cat_Description]) VALUES (N'EA3D573E-EBD6-4DBE-A5CB-36DAB14F9EF9', N'Computers & Technology', 0, N'Computers & Technology')
INSERT [dbo].[Categories] ([Cat_Id], [Cat_Name], [Cat_isDelete], [Cat_Description]) VALUES (N'EDA38AE3-47F6-41FB-8194-515F56C6F8E6', N'Medical Books', 0, N'Medical Books')
INSERT [dbo].[Categories] ([Cat_Id], [Cat_Name], [Cat_isDelete], [Cat_Description]) VALUES (N'FE232B72-C46B-4242-80D8-30A6E8F58F62', N'History', 0, N'History')
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'02A9CC68-3491-4595-9D5A-FDAD84E11B89', N'150120110129003', N'9780871407900', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'07568609-69C8-4F5E-8AC0-8D531349BDA9', N'150120110655003', N'9780345539816', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'11F77D4C-ECC5-4C21-87EA-41FE6265601E', N'150120110742002', N'9781455551927', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'16CD46BD-EA79-45BB-AEC8-AC350FEFFFFC', N'150120110826001', N'9781484205396', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'20AB67DB-0017-4DEA-9945-2DCB14A2448C', N'150120110407001', N'9781594633669', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'3691D39A-597A-4645-A4AB-BE7FF59EB949', N'150120110826002', N'9781484205396', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'3CE0F5C0-4B5C-4D9F-9FF8-F1FC2BD5A582', N'150120110553003', N'9780770433246', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'46EFCAD9-54BD-4E66-B05F-2964CF518771', N'150120110129001', N'9780871407900', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'5EE7F447-463C-4142-AA45-555848B9948B', N'150120105943002', N'9780670785957', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'5F531F7A-0529-483E-950D-04449B5291D0', N'150120110306003', N'9780544309876', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'610A18D0-1C43-45B4-9B05-53339D306273', N'150120110742001', N'9781455551927', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'67A92F16-0C93-46B0-AA6D-DC27B666D402', N'150120110306001', N'9780544309876', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'699D018D-24D6-4ED0-87A1-405CC50A73D4', N'150120110218002', N'9781594205866', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'6D19212A-94BE-4A9D-AEEA-A094E16A2398', N'150120110218003', N'9781594205866', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'6E66DDA8-7A69-41AA-86D1-9AEF1E97EB73', N'150120110655001', N'9780345539816', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'7294C57C-50E3-4589-BB1B-7262C086F003', N'150120110450001', N'9781439172568', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'7B2F9162-8A04-4DB0-A133-B79464D60DD3', N'150120110407003', N'9781594633669', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'89E59C08-E193-4846-A32E-33C30AC73B14', N'150120105740003', N'9780316373807', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'9D9DDED7-CA25-4001-9EA3-DFB8B3938444', N'150120110742003', N'9781455551927', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'A318CA87-04F7-48A6-BB32-AD941879AD8C', N'150120110218001', N'9781594205866', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'A4994ED2-4C22-493F-BBE4-C2028A5D4E50', N'150120105943003', N'9780670785957', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'A6DAAB37-DBCB-422B-9BEC-2E30190D8097', N'150120110129002', N'9780871407900', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'CB226701-0C34-4ADD-B238-FB53D0C183E7', N'150120110553001', N'9780770433246', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'CBB78F93-0108-4F78-8F85-E097ECB96237', N'150120110450002', N'9781439172568', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'CFFC74D0-854D-49E1-8250-CA3B797E956B', N'150120110306002', N'9780544309876', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'D2940EC3-4127-4B0C-A63A-677D957EE062', N'150120110827003', N'9781484205396', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'DBF3BD9A-04A6-49DE-941D-8E486284D960', N'150120110553002', N'9780770433246', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'F056F4F7-0C92-40ED-95B2-EADCBA51FCE9', N'150120105740001', N'9780316373807', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'F08445F3-58E2-4640-898C-7334268359B7', N'150120105943001', N'9780670785957', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'F4AAA6FB-BC2D-41FF-AC90-CEE5C16B3938', N'150120110450003', N'9781439172568', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'F8271603-50AB-445B-853E-6189BE741501', N'150120110655002', N'9780345539816', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'FA7BC850-8B9E-4729-8FA5-4FCF869967FE', N'150120110407002', N'9781594633669', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'FC3A555E-AEB6-464F-9CDE-249BD4BC10B1', N'150120105740002', N'9780316373807', 1, 0)
SET ANSI_PADDING ON

GO
/****** Object:  Index [UQ__Categori__B46D3EC36E0FDC26]    Script Date: 1/20/2015 11:15:41 AM ******/
ALTER TABLE [dbo].[Categories] ADD UNIQUE NONCLUSTERED 
(
	[Cat_Name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON

GO
/****** Object:  Index [UQ__Copies__D0CC2A0E8E8DF504]    Script Date: 1/20/2015 11:15:41 AM ******/
ALTER TABLE [dbo].[Copies] ADD UNIQUE NONCLUSTERED 
(
	[Cop_No] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Books] ADD  DEFAULT ('') FOR [Book_Content]
GO
ALTER TABLE [dbo].[Books] ADD  DEFAULT ('/image/nocover.png') FOR [Book_ImageFile]
GO
ALTER TABLE [dbo].[Books] ADD  DEFAULT (getdate()) FOR [Book_CreateDate]
GO
ALTER TABLE [dbo].[Books] ADD  DEFAULT ((0)) FOR [Book_isDeleted]
GO
ALTER TABLE [dbo].[Categories] ADD  DEFAULT (newid()) FOR [Cat_Id]
GO
ALTER TABLE [dbo].[Categories] ADD  DEFAULT ((0)) FOR [Cat_isDelete]
GO
ALTER TABLE [dbo].[Copies] ADD  DEFAULT (newid()) FOR [Cop_Id]
GO
ALTER TABLE [dbo].[Copies] ADD  DEFAULT ((1)) FOR [Cop_Status]
GO
ALTER TABLE [dbo].[Copies] ADD  DEFAULT ((0)) FOR [Cop_isDeleted]
GO
ALTER TABLE [dbo].[Books]  WITH CHECK ADD FOREIGN KEY([Cat_Id])
REFERENCES [dbo].[Categories] ([Cat_Id])
GO
ALTER TABLE [dbo].[Copies]  WITH CHECK ADD FOREIGN KEY([Book_ISBN])
REFERENCES [dbo].[Books] ([Book_ISBN])
GO
