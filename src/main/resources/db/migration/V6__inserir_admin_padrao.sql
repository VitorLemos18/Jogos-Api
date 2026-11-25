INSERT INTO usuarios (
    id,
    email,
    senha,
    nome,
    cpf,
    role,
    data_nascimento,
    create_at
) VALUES (
    '00000000-0000-0000-0000-000000000001',
    'admin@aokrys.com',
    '$2a$10$crEAfjA3teiWl.adYvlKZekTW1Qlcr6NJKPQaH2TT3goM4xVIC1Ve',
    'Administrador',
    '00000000000',
    'ADMIN',
    '1990-01-01',
    NOW()
) ON CONFLICT (id) DO NOTHING;
