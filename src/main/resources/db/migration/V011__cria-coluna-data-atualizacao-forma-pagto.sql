alter table forma_pagamento add column data_atualizacao datetime null;
update forma_pagamento set forma_pagamento.data_atualizacao = utc_timestamp;
alter table forma_pagamento modify column data_atualizacao datetime not null;