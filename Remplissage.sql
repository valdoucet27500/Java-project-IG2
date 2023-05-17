-- Insertion dans la table 'tool' pour un cadre de bar
INSERT INTO
    tool (tool_name)
VALUES
    ('Shaker'),
    ('Verre à mélange'),
    ('Cuillère de bar'),
    ('Pilon à cocktail'),
    ('Passoire à cocktail'),
    ('Jigger'),
    ('Pilon à glace'),
    ('Pince à glace'),
    ('Pince à fruits'),
    ('Couteau à zeste'),
    ('Pique à cocktail'),
    ('Muddler'),
    ('Décapsuleur'),
    ('Presse-agrumes'),
    ('Entonnoir'),
    ('Râpe à muscade'),
    ('Passoire à thé'),
    ('Paille'),
    ('Moulin à cocktail'),
    ('Mixeur plongeant'),
    ('Mortier'),
    ('Moule à glaçons'),
    ('Pilon à sucre'),
    ('Doseur de sirop'),
    ('Bouteille verseuse'),
    ('Passoire fine'),
    ('Ciseaux à découper les herbes'),
    ('Tire-bouchon'),
    ('Pilon à menthe'),
    ('Tamis');

-- Insertion dans la table 'unit' pour des unités de mesure
INSERT INTO
    unit (unit_name)
VALUES
    ('gramme'),
    ('kilogramme'),
    ('millilitre'),
    ('centilitre'),
    ('litre'),
    ('cuillère à soupe'),
    ('cuillère à café'),
    ('verre'),
    ('tasse'),
    ('once'),
    ('livre'),
    ('unité');

-- Insertion dans la table 'consumable_type' pour des types de consommables
INSERT INTO
    consumable_type (consumable_type_name)
VALUES
    ('Ingrédient'),
    ('Boisson'),
    ('Épice'),
    ('Condiment'),
    ('Produit laitier'),
    ('Légume'),
    ('Fruit'),
    ('Viande'),
    ('Poisson'),
    ('Féculent');

-- Insertion dans la table 'alcohol_type' pour des types de boissons
INSERT INTO
    alcohol_type (alcohol_type_name)
VALUES
    ('Vin'),
    ('Bière'),
    ('Spiritueux'),
    ('Liqueur'),
    ('Cidre'),
    ('Eau'),
    ('Jus de fruits'),
    ('Soda'),
    ('Thé'),
    ('Café');

-- Ajout de consommables (boissons) dans la table 'consumable'
INSERT INTO
    consumable (
        consumable_name,
        is_vegan,
        unit_id,
        description,
        kcal,
        creation_date,
        consumable_type_id
    )
VALUES
    (
        'Vin rouge',
        1,
        'litre',
        'Un délicieux vin rouge',
        120,
        '2023-05-17',
        'Boisson'
    ),
    (
        'Bière blonde',
        1,
        'litre',
        'Une bière blonde rafraîchissante',
        150,
        '2023-05-17',
        'Boisson'
    ),
    (
        'Whisky',
        1,
        'once',
        'Un whisky de qualité',
        100,
        '2023-04-17',
        'Boisson'
    ),
    (
        'Mojito',
        1,
        'litre',
        'Un célèbre cocktail rafraîchissant',
        200,
        '2023-05-17',
        'Boisson'
    ),
    (
        'Eau plate',
        1,
        'litre',
        'Eau plate naturelle',
        0,
        '2023-05-29',
        'Boisson'
    ),
    (
        "Jus d'orange",
        1,
        'litre',
        "Jus d'orange pressé frais",
        80,
        '2023-05-17',
        'Boisson'
    ),
    (
        'Soda cola',
        1,
        'litre',
        'Un soda cola pétillant',
        140,
        '2023-05-17',
        'Boisson'
    ),
    (
        'Thé vert',
        1,
        'litre',
        'Thé vert japonais',
        0,
        '2024-05-17',
        'Boisson'
    ),
    (
        'Café expresso',
        1,
        'litre',
        'Un café expresso corsé',
        5,
        '2023-11-17',
        'Boisson'
    );

-- Ajout des types de boissons dans la table 'drink'
INSERT INTO
    drink (
        drink_name,
        alcohol_type_id,
        is_sugar_free,
        is_sparkling,
        alcohol_level
    )
VALUES
    ('Vin rouge', 'Vin', 0, 0, 12),
    ('Bière blonde', 'Bière', 0, 1, 5),
    ('Whisky', 'Spiritueux', 0, 0, 40),
    ('Mojito', 'Liqueur', 0, 1, 12),
    ('Eau plate', 'Eau', 1, 0, 0),
    ("Jus d'orange", 'Jus de fruits', 1, 0, 0),
    ('Soda cola', 'Soda', 0, 1, 0),
    ('Thé vert', 'Thé', 1, 0, 0),
    ('Café expresso', 'Café', 1, 0, 0);

-- Ajout de consommables qui ne sont pas des boissons dans la table 'consumable'
INSERT INTO
    consumable (
        consumable_name,
        is_vegan,
        unit_id,
        description,
        kcal,
        creation_date,
        consumable_type_id
    )
VALUES
    (
        'Citron',
        1,
        'unité',
        'Citron jaune frais',
        30,
        '2023-05-27',
        'Fruit'
    ),
    (
        'Orange',
        1,
        'unité',
        'Orange fraîche',
        40,
        '2023-05-20',
        'Fruit'
    ),
    (
        'Menthe',
        1,
        'gramme',
        'Feuilles de menthe fraîche',
        0,
        '2023-05-17',
        'Épice'
    ),
    (
        'Glaçon',
        1,
        'unité',
        'Glaçon',
        0,
        '2023-05-17',
        'Ingrédient'
    ),
    (
        'Sirop de grenadine',
        1,
        'centilitre',
        'Sirop de grenadine',
        60,
        '2023-05-11',
        'Condiment'
    ),
    (
        'Sirop de sucre de canne',
        1,
        'centilitre',
        'Sirop de sucre de canne',
        50,
        '2023-05-17',
        'Condiment'
    ),
    (
        'Sirop de menthe',
        1,
        'centilitre',
        'Sirop de menthe',
        70,
        '2023-06-17',
        'Condiment'
    ),
    (
        'Viande de poulet',
        0,
        'gramme',
        'Viande de poulet désossée',
        250,
        '2023-05-17',
        'Viande'
    ),
    (
        'Fromage',
        0,
        'gramme',
        'Fromage affiné',
        150,
        '2023-05-17',
        'Produit laitier'
    ),
    (
        'Beurre',
        0,
        'gramme',
        'Beurre doux',
        100,
        '2023-05-17',
        'Produit laitier'
    );