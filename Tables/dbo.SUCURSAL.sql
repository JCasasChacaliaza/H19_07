CREATE TABLE [dbo].[SUCURSAL]
(
[IDSUC] [int] NOT NULL IDENTITY(1, 1),
[TELSUC] [char] (9) COLLATE Modern_Spanish_CI_AS NULL,
[DIRESUC] [varchar] (150) COLLATE Modern_Spanish_CI_AS NULL,
[PROSUC] [varchar] (150) COLLATE Modern_Spanish_CI_AS NULL,
[ESTSUC] [char] (1) COLLATE Modern_Spanish_CI_AS NULL
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[SUCURSAL] ADD CONSTRAINT [SUCURSAL_pk] PRIMARY KEY CLUSTERED  ([IDSUC]) ON [PRIMARY]
GO
