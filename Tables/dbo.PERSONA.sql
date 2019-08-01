CREATE TABLE [dbo].[PERSONA]
(
[IDPER] [int] NOT NULL IDENTITY(1, 1),
[NOMPER] [varchar] (150) COLLATE Modern_Spanish_CI_AS NULL,
[APEPER] [varchar] (150) COLLATE Modern_Spanish_CI_AS NULL,
[DNIPER] [char] (8) COLLATE Modern_Spanish_CI_AS NULL,
[SEXPER] [char] (1) COLLATE Modern_Spanish_CI_AS NULL,
[CELPER] [char] (9) COLLATE Modern_Spanish_CI_AS NULL,
[TIPPER] [char] (1) COLLATE Modern_Spanish_CI_AS NULL,
[USUPER] [varchar] (150) COLLATE Modern_Spanish_CI_AS NULL,
[PASSPER] [varchar] (50) COLLATE Modern_Spanish_CI_AS NULL,
[ESTPER] [char] (1) COLLATE Modern_Spanish_CI_AS NULL
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[PERSONA] ADD CONSTRAINT [PERSONA_pk] PRIMARY KEY CLUSTERED  ([IDPER]) ON [PRIMARY]
GO
