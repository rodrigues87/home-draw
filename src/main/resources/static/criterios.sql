INSERT INTO public.criterio (id, descricao, limite_inferior, limite_superior, pontuacao, tipo)
VALUES (1, 'Renda total da família até 900 reais', 0.00, 900.00, 5, 'RENDA');

INSERT INTO public.criterio (id, descricao, limite_inferior, limite_superior, pontuacao, tipo)
VALUES (2, 'Renda total da família de 901 à 1500 reais', 901.00, 1500.00, 3, 'RENDA');

INSERT INTO public.criterio (id, descricao, limite_inferior, limite_superior, pontuacao, tipo)
VALUES (3, 'Famílias com 3 ou mais dependentes', 3.00, 100.00, 3, 'DEPENDENTES');

INSERT INTO public.criterio (id, descricao, limite_inferior, limite_superior, pontuacao, tipo)
VALUES (4, 'Famílias com 1 ou 2 dependentes', 1.00, 2.00, 2, 'DEPENDENTES');