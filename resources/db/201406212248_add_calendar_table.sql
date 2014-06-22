CREATE Table "calevent" (
  "id" INTEGER PRIMARY KEY AUTOINCREMENT,
  "form_id" bigint(20) NOT NULL,
  "start" datetime NOT NULL,
  "end" datetime NOT NULL
);

insert into Version (timestamp) values ("201406212248");
