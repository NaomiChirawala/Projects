import mysql.connector as m
con=m.connect(host='localhost', user='root', password='naomi', database='project')
cur=con.cursor()
#Drop tables if they exist
cur.execute('drop table if exists Step1')
con.commit()
cur.execute('drop table if exists Step2')
con.commit()
#Creating table Alkylhalide and inserting records
cur.execute('''create table Step1(Alkylhalide char(20), Reagent char(40),
Step_1_Product char(40))''')
cur.execute('''insert into Step1 values
("Chloromethane","Alcoholic KOH","No Reaction"),
("Chloroethane","Alcoholic KOH","Ethene"),
("Chloropropane","Alcoholic KOH","Propene"),
("Chlorobutane","Alcoholic KOH","Butene"),
("Chloropentane","Alcoholic KOH","Pentene"),
("Chlorohexane","Alcoholic KOH","Hexene"),

("Chloromethane","Mg ether","Methyl magnesium chloride"),
("Chloroethane","Mg ether","Ethyl magnesium chloride"),
("Chloropropane","Mg ether","Propyl magnesium chloride"),
("Chlorobutane","Mg ether","Butyl magnesium chloride"),
("Chloropentane","Mg ether","Pentyl magnesium chloride"),
("Chlorohexane","Mg ether","Hexyl magnesium chloride"),

("Chloromethane","Aqueous KOH","Methanol"),
("Chloroethane","Aqueous KOH","Ethanol"),
("Chloropropane","Aqueous KOH","Propanol"),
("Chlorobutane","Aqueous KOH","Butanol"),
("Chloropentane","Aqueous KOH","Pentanol"),
("Chlorohexane","Aqueous KOH","Hexanol"),

("Chloromethane","NH3","Methanamine"),
("Chloroethane","NH3","Ethanamine"),
("Chloropropane","NH3","Propanamine"),
("Chlorobutane","NH3","Butanamine"),
("Chloropentane","NH3","Pentanamine"),
("Chlorohexane","NH3","Hexanamine")
''')
con.commit()
#Creating table Step2 and inserting records
cur.execute('''create table Step2 (Step_1_Product char(30), Reagent2 char(30),
Step_2_Product char(30))''')
con.commit()
cur.execute('''insert into Step2 values

('Methyl magnesium chloride','HCHO','Ethanol'),
('Ethyl magnesium chloride','HCHO','Propanol'),
('Propyl magnesium chloride','HCHO','Butanol'),
('Butyl magnesium chloride','HCHO','Pentanol'),
('Pentyl magnesium chloride','HCHO','Hexanol'),
('Hexyl magnesium chloride','HCHO','Heptanol'),

('Methyl magnesium chloride','CO2','Ethanoic Acid'),
('Ethyl magnesium chloride','CO2','Propanoic Acid'),
('Propyl magnesium chloride','CO2','Butanoic Acid'),
('Butyl magnesium chloride','CO2','Valeric Acid'),
('Pentyl magnesium chloride','CO2','Caproic Acid'),
('Hexyl magnesium chloride','CO2','Enanthic Acid'),

('Ethene','Br2+Alc KOH','Ethyne'),
('Propene','Br2+Alc KOH','Propyne'),
('Butene','Br2+Alc KOH','Butyne'),
('Pentene','Br2+Alc KOH','Pentyne'),
('Hexene','Br2+Alc KOH','Hexyne'),

('Methanol','Cu|573K','Methanal'),
('Ethanol','Cu|573K','Ethanal'),
('Propanol','Cu|573K','Propanal'),
('Butanol','Cu|573K','Butanal'),
('Pentanol','Cu|573K','Pentanal'),
('Hexanol','Cu|573K','Hexanal')
''')
con.commit()

while True:
    z=int(input('''\n\nWelcome to ChemHelp!
    Enter what you want to do:
    1. How to get a compound from an Alkylhalide
    2. How an Alkylhalide reacts with a Reagent
    3. Exit: '''))
    print('\n')
    if z==1:
        a=input('''Enter final product and we will tell you how to get it
        from an alkyl halide :)
        Available products are
        -> Alkenes
        -> Alkyne
        -> Alcohol
        -> Aldehyde
        -> Amine
        -> Carboxylic Acid
        -> Grignard Reagent
        Please enter IUPAC name of final product: ''')
        print('\n')
        print('Reaction')
        cur.execute('''select * from Step1 B left join Step2 A on
        A.Step_1_Product=B.Step_1_Product
        where A.Step_2_Product="{}" or B.Step_1_Product="{}"'''.format(a,a))
        d=cur.fetchall()
        for i in d:
            for j in i:
                if j=='None':
                    i=tuple((list(i)).pop())
            print(i[0], '---'+i[1]+'-->', i[2], end='')
            if i[2]==a:
                break
            for k in range(1):
                try:
                    print('---'+i[4]+'-->', i[5])
                except:
                    break
    if z==2:
        print('Available Reactants are:')
        cur.execute('select distinct Alkylhalide from Step1')
        e=cur.fetchall()
        for i in e:
            j=str(i)
            h=j.lstrip("('")
            k=h.rstrip("',)")
            print(k)
        print('\n')
        print('Available Reagents are:')
        cur.execute('select distinct Reagent from Step1')
        e=cur.fetchall()
        for i in e:
            j=str(i)
            h=j.lstrip("('")
            k=h.rstrip("',)")
            print(k)
        print('\n')

        b=input('Enter the reactant name: ')
        c=input('Enter the reagent name: ')
        print('\n')
        print('Reaction:')
        cur.execute('''select * from Step1 B where
        (B.Alkylhalide="{}" and B.Reagent="{}")'''.format(b,c))
        d=cur.fetchall()
        for i in d:
            for j in i:
                if j=='None':
                    i=tuple((list(i)).pop())
            print(i[0], '---'+i[1]+'-->', i[2], end='')

    if z==3:
        break



        
