# -*- coding: utf-8 -*-
'''''/*******************************************************************************
Autor: Eduardo Fernandes de Brito Pereira
Componente Curricular: Algoritmos I
Concluido em: 08/08/2019
Declaro que este código foi elaborado por mim de forma individual e não contém nenhum
trecho de código de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.
******************************************************************************************/'''''
def dicionario_tecnicos_regioes(txt,elemento_2, ultimo_elemento):
    '''Função que abre o arquivo Regioes ou o arquivo Tecnicos
    e os devolve em forma de dicionario onde as chaves do dicio_tecnicos são as matriculas
    e as chaves do dicio_regioes são os CodigosIBGE.'''
    dicionario_pronto = {}
    arquivo = open(txt, "r", encoding="utf-8")
    for i in arquivo:
        if "Matrícula" in i or "Município" in i:
            linha_arquivo = arquivo.readline()
        else:
            linha_arquivo = i
        lista_arquivo = linha_arquivo.strip()
        dermacador_arquivo = lista_arquivo.split(";")
        if txt == "tecnicosIBGE.txt":
            dicionario_linhas = {dermacador_arquivo[0]:dermacador_arquivo[(
                elemento_2):(ultimo_elemento)]}
        if txt == "regioes.txt":
            dicionario_linhas = {dermacador_arquivo[2]:dermacador_arquivo}
        dicionario_pronto.update(dicionario_linhas)
    arquivo.close()
    return dicionario_pronto

def dicionario_pesquisa():
    '''Função que abre o arquivo exemploPesquisa e o devolve em forma de dicionario, onde
    as chaves do dicio_pesquisa equivalem ao numero da linha que foi transformada.'''
    dicionario_pesquisa = {}
    codLinha = 0 #Contador de linhas que fará o papel de chave no dicio_pesquisa
    file_pesquisa = open("exemploPesquisa.txt", "r", encoding="UTF-8")
    for i in file_pesquisa:
        if "Técnico" in i:
            linha_arquivo = file_pesquisa.readline()
        else:
            linha_arquivo = i
        lista_arquivo = linha_arquivo.strip()
        dermacador_arquivo = lista_arquivo.split(";")
        dicionario_linhas = {f"Linha{codLinha+1}":dermacador_arquivo}
        dicionario_pesquisa.update(dicionario_linhas)
        codLinha += 1
    file_pesquisa.close()
    return dicionario_pesquisa

def verificar_cadastros(nome_arquivo,dicionario_pesquisa):
    '''Função que verifica os tecnicos cadastrados e as cidades registradas,
    ela funciona da seguinte forma: Verifica se o 1° elemento do dicio_pesquisa está entre as chaves
    do dicio_tecnicos, ou seja, se as mastriculas do dicio_pesquisa estão cadastradas, caso encontre alguma
    que não esteja, essa função devolve uma lista das matriculas sem cadastro, possibilitando assim a correção
    do arquivo. Funciona analogamente para cidades, a unica diferença é que ela verifica o 2° elemento do
    dicio_pesquisa.'''
    cadastrados = 0
    sem_cadastro = 0
    faltando = []
    tecnicos = dicionario_tecnicos_regioes("tecnicosIBGE.txt", 1,4)
    cidade = dicionario_tecnicos_regioes("regioes.txt", 1,6)
    for item in dicionario_pesquisa:
        linhaDicio = dicionario_pesquisa.get(item)
        if nome_arquivo == "tecnicos":#verificação dicio_tecnios/dicio_pesquisa
            if linhaDicio[0] in tecnicos.keys():
                cadastrados +=1
                tecnicos_confirmados = "Todos os tecnicos estão confirmados"
            else:
                sem_cadastro +=1
                faltando.append(linhaDicio[0])
                tecnicos_erro = faltando
        if nome_arquivo == "cidades":#verificação dicio_cidades/dicio_pesquisa
            if linhaDicio[1] in cidade.keys():
                cadastrados +=1
                cidades_confirmadas = "Todas as cidades estão cadastradas"
            else:
                sem_cadastro +=1
                faltando.append(linhaDicio[1])
                cidades_erro = faltando
    if nome_arquivo == "tecnicos":
        if sem_cadastro > 0:
            return tecnicos_erro #retornando lista de tecnicos sem matricula
        else:
            return tecnicos_confirmados
    if nome_arquivo == "cidades":
        if sem_cadastro > 0:
            return cidades_erro #retornando lista de cidades sem cadastro
        else:
            return cidades_confirmadas

def estatistica_1():
    '''Função que conta o numero de linhas arquivo exemploPesquisa,
    pois o numero de linhas do arquivo equivale ao numero de domicilios pesquisados'''
    contador_linhas = 1
    file_pesquisa = open("exemploPesquisa.txt", "r", encoding="UTF-8")
    for linha in file_pesquisa:
        if "Técnico" in linha: #Ignorar a primeira linha
            contador_linhas -= 1
        else:
            contador_linhas += 1
    file_pesquisa.close()
    return contador_linhas

def estatistica_2(dicionario_pesquisa):
    '''Função que verifica se a resposta 1.02 do arquivo pesquisa está dentre as alternativas aceitas,
    caso estejam, o 6º elemento do dicio_pesquisa é computado entre pago, pagando e alugando e devolvidos em
    forma de dicionario, caso não estejam, é reportado para o usuario que ha um erro na linha'''
    pago = 0
    pagando = 0
    alugado = 0
    for item in dicionario_pesquisa:
        linhaDicio = dicionario_pesquisa.get(item)
        codIBGECidade = linhaDicio[1]
        if linhaDicio[3] == "1" or linhaDicio[3] == "5" or linhaDicio[3] == "6":
            if linhaDicio[5] == "1":
                pago += 1
            elif linhaDicio[5] == "2":
                pagando += 1
            elif linhaDicio[5] == "3":
                alugado += 1
            domicilios = [pago,pagando,alugado]
        else:
            mensagem = (f"Resposta 1.02 incorreta no municipio de Codigo: {codIBGECidade}")
            return mensagem #retornando erro na linha equivalente
    return domicilios #retornando dicionario

def estatistica_3(dicionario_pesquisa):
    '''Função que verifica se a resposta 2.02 esta dentre as respostas aceitas,
    caso estejam, devolve um dicionario dividido por cidade onde os valores são,
    quantos domicilios naquela cidade tem banheiro e quantos não, caso não estejam
    devolve a linha que foge dos padroes para que o usuario possa corrigila'''
    dicionario_banheiros = {}
    for item in dicionario_pesquisa:
        linhaDicio = dicionario_pesquisa.get(item)
        codIBGECidade = linhaDicio[1]
        tem_banheiro = True
        try: #Verificação resposta correta
            num = int(linhaDicio[6])
            if num <0:
                raise Exception
        except:
            mensagem = (f"Resposta 2.02 incorreta no municipio de Codigo: {codIBGECidade}")
            return mensagem #devolve erro caso encontre
        if linhaDicio[6] == "0":
            tem_banheiro = False
        if codIBGECidade in dicionario_banheiros:
            if tem_banheiro:
                dicionario_banheiros.get(codIBGECidade)[0] += 1
            else:
                dicionario_banheiros.get(codIBGECidade)[1] += 1
        else:
            if tem_banheiro:
                dicionario_banheiros[codIBGECidade] = [1,0]
            else:
                dicionario_banheiros[codIBGECidade] = [0, 1]
    return dicionario_banheiros

def estatistica_4():
    '''Função que verifica se a resposta 2.05 esta correta, caso esteja
    cria um dicionario dividido por cidade, e adiciona naquela cidade
    os valores para forma de abastecimento, caso não estejam devolve erro
    para que o usuario possa corrigilo'''
    dicio_abastecimento = {}
    for item in dicionario_pesquisa():
        linhaDicio = dicionario_pesquisa().get(item)
        codCidade = linhaDicio[1]
        if codCidade not in dicio_abastecimento:
        #Caso não haja uma chave com o codigo daquela cidade, essa chave é criada
            dicio_abastecimento[codCidade] = [linhaDicio[9]]
        else:
        #Caso ja exista, se adiciona naquela chave a resposta 2.05
            if linhaDicio[9] == "1":
                dicio_abastecimento.get(codCidade).append(linhaDicio[9])
            elif linhaDicio [9] == "2":
                dicio_abastecimento.get(codCidade).append(linhaDicio[9])
            elif linhaDicio [9] == "3":
                dicio_abastecimento.get(codCidade).append(linhaDicio[9])
            elif linhaDicio [9] == "4":
                dicio_abastecimento.get(codCidade).append(linhaDicio[9])
            elif linhaDicio [9] == "5":
                dicio_abastecimento.get(codCidade).append(linhaDicio[9])
            elif linhaDicio [9] == "6":
                dicio_abastecimento.get(codCidade).append(linhaDicio[9])
            elif linhaDicio [9] == "7":
                dicio_abastecimento.get(codCidade).append(linhaDicio[9])
            elif linhaDicio [9] == "8":
                dicio_abastecimento.get(codCidade).append(linhaDicio[9])
            elif linhaDicio [9] == "9":
                dicio_abastecimento.get(codCidade).append(linhaDicio[9])
            elif linhaDicio [9] == "10":
                dicio_abastecimento.get(codCidade).append(linhaDicio[9])
            else:
                mensagem = (f"Resposta 2.05 incorreta no municopio de Codigo: {codCidade}")
                return mensagem #Reportando erro pra o usuario
    return dicio_abastecimento

def estatistica_5(dicionario_pesquisa):
    '''Função que verifica se a resposta 2.07 essa dentre as opções validas,
    caso esteja, cria um dicionario dividido por cidade, e adiciona naquela cidade
    quantos domicilios possuem energia sendo essa de companhia distribuidora ou nao, e quantos
    domicilios não possuem energia, caso não estejam, reporta um erro para que o usuario corrija'''
    dicionario_energia = {}
    for item in dicionario_pesquisa:
        linhaDicio = dicionario_pesquisa.get(item)
        codIBGECidade = linhaDicio [1]
        tem_energia = True
        try:#Verificação valores validos
            linhaDicio == "1" or linhaDicio == "2" or linhaDicio == "3"
        except:
            mensagem = (f"Resposta 2.07 incorreta no municipio de Codigo: {codIBGECidade}")
            return mensagem #Reportando erro
        if linhaDicio [11]=="3":
            tem_energia = False
        if codIBGECidade in dicionario_energia:
        #Se ja existir uma chave com aquela cidade, adiciona valores nas chaves
            if tem_energia:
                if linhaDicio[11]=="1":#Se a resposta for energia atravez de distribuidora
                    dicionario_energia.get(codIBGECidade)[0] += 1#Adiciona +1 na chave energia distribuidora
                else:#Se for energia atravez de outras formas
                    dicionario_energia.get(codIBGECidade)[1] += 1#Soma +1 na chave energia outras formas
            else:#Caso não tenha energia
                dicionario_energia.get(codIBGECidade) [2] += 1#Adiciona +1 na chave Sem energia
        else :
        #Caso não exista uma chave com aquela cidade ela é criada
            if tem_energia:
                if linhaDicio [11]=="1":
                    dicionario_energia [codIBGECidade] = [1, 0, 0]
                else:
                    dicionario_energia [codIBGECidade] = [0, 1, 0]
            else:
                dicionario_energia [codIBGECidade] = [0, 0, 1]
    return dicionario_energia

def estatistica_6(dicionario_pesquisa):
    '''Função que verifica a resposta 4.03 e armazena em chaves dividada por raças, caso a resposta
    esteja fora das opçoes aceitas, reporta um erro pro usuario para que possa ser corrigido'''
    dicionario_raça = {"total":0}
    for item in dicionario_pesquisa:
        linhaDicio = dicionario_pesquisa.get(item)
        raça = linhaDicio[18]
        codIBGECidade = linhaDicio[1]
        if raça == "1":
            raça = "branca"
        elif raça == "2":
            raça = "preta"
        elif raça == "3":
            raça = "amarela"
        elif raça == "4":
            raça = "parda"
        elif raça == "5":
            raça = "indigena"
        else:
            mensagem = (f"Resposta 4.03 incorreta no municipio de Codigo: {codIBGECidade}")
            return mensagem#Reportando erro ao usuario
        if raça in dicionario_raça:#Se existir a raça entre as chaves, adiciona valor aquela determinada chave
            dicionario_raça.get(raça)[0] += 1
        else:#Caso não exista, aquela raça é adicionada a uma chave
            dicionario_raça[raça] = [0]
        dicionario_raça["total"] += 1
    return dicionario_raça

def estatistica_7(dicionario_pesquisa):
    '''Função que verifica os estados dos municipios no dicio_Pesquisa e os sepera por regioes em um
    dicionario e caso aquele estado nao exista, expoe um erro pro usuario'''
    dicio_regioes = {"Nordeste":0, "Norte":0, "Centro-Oeste":0, "Sudeste":0, "Sul":0}
    regioes = dicionario_tecnicos_regioes("regioes.txt", 1,6)
    for item in dicionario_pesquisa:
        linhaDicio = dicionario_pesquisa.get(item)
        estado = regioes[linhaDicio[1]][3]
        codIBGECidade = linhaDicio[1]
        if estado == "BA" or estado == "AL" or estado == "SE" or estado == "PB" or estado == "PE" \
                or estado == "RN" or estado == "CE" or estado == "PI" or estado == "MA":
            dicio_regioes["Nordeste"] += 1
        elif estado == "AM" or estado == "RR" or estado == "AP" or estado == "PA" or estado == "TO" \
                or estado == "RO" or estado == "AC":
            dicio_regioes ["Norte"] += 1
        elif estado == "MT" or estado == "MS" or estado == "GO":
            dicio_regioes["Centro-Oeste"] +=1
        elif estado == "SP" or estado == "RJ" or estado == "ES" or estado == "MG":
            dicio_regioes["Sudeste"] +=1
        elif estado == "PR" or estado == "RS" or estado == "SC":
            dicio_regioes["Sul"] +=1
        else:
            mensagem = (f"Erro, estado incorreto no municipio de Codigo: {codIBGECidade}")
            return mensagem #Retornando erro pra o usuario para que ele possa corrigilo
    return dicio_regioes

def opção_menu(escolha_ou_continua):
    #Função para garantir o looping do MENU
    if escolha_ou_continua == "escolha":
        while True:
            try:
                escolha = input("Qual sua escolha?\n")
                escolha = int(escolha)
                if escolha >0 and escolha <10:
                    return escolha
                else:
                    raise Exception
            except:
                return opção_menu("escolha")
    if escolha_ou_continua == "continua":
        while True:
                continua = input("Deseja visualizar outra estatistica? S/N\n")
                if continua == "S" or continua == "s" or continua == "N" or continua == "n":
                    return continua
                else:
                    return opção_menu("continua")

def menu_opçoes():
    #Menu de opções para visualizar os resultados
    import sys
    import time
    import os
    print("[1] - Números de domicílios utilizados para a coleta.")
    print("[2] - Número de domicílios particulares que já estão pagos, "
            "quantos ainda estão pagando e alugados.")
    print("[3] - Quantos domicílios por cidade possuem banheiro e quantos não possuem.")
    print("[4] - A forma mais comum de abastecimento de água por cidade.")
    print("[5] - O percentual de domicílios por cidade que ainda não possuem energia elétrica")
    print("[6] - O percentual de moradores que participaram da entrevista por cor ou raça.")
    print("[7] - A região com maior número de municípios pesquisados.")
    print("[8] - Todas as estatisticas.")
    print("[9] - Finalizar Programa")
    escolha = opção_menu("escolha")
    if escolha == 1:
        print("==============================================")
        print("NUMERO DE DOMICILIOS UTILIZADOS PARA A COLETA.")
        print("==============================================")
        time.sleep(0.5)
        resultado_1()
    elif escolha == 2:
        print("============================================================================================")
        print("NUMERO DE DOMICILIOS PARTICULARES QUE JA ESTÃO PAGOS,QUANTOS AINDA ESTÃO PAGANDO"
              "E ALUGADOOS.")
        print("============================================================================================")
        time.sleep(0.5)
        resultado_2()
    elif escolha == 3:
        print("======================================================================")
        print("QUANTOS DOMICILIOS POR CIDADE POSSUEM BANHEIRO E QUANTOS NÃO POSSUEM.")
        print("======================================================================")
        time.sleep(1)
        resultado_3()
    elif escolha == 4:
        print("==========================================================")
        print("A FORMA MAIS COMUM DE ABASTECIMENTO DE AGUA POR CIDADE.")
        print("==========================================================")
        time.sleep(1)
        resultado_4()
    elif escolha == 5:
        print("=============================================================================")
        print("O PERCENTUAL DE DOMICILIOS POR CIDADE QUE AINDA NÃO POSSUEM ENERGIA ELETRICA.")
        print("=============================================================================")
        time.sleep(1)
        resultado_5()
    elif escolha == 6:
        print("===========================================================================")
        print("O PERCENTUAL DE MORADORES QUE PARTICIPARAM DA ENTREVISTA POR COR OU RAÇA.")
        print("===========================================================================")
        time.sleep(0.5)
        resultado_6()
    elif escolha == 7:
        print("========================================================")
        print("A REGIÃO COM MAIOR NUMERO DE MUNICIPIOS PESQUISADOS.")
        print("========================================================")
        resultado_7()
    elif escolha == 8:
        print("[1] - Números de domicílios utilizados para a coleta.")
        resultado_1()
        prosseguir = input("Aperte qualquer tecla para visualizar a proxima")
        os.system('cls')
        print("[2] - Número de domicílios particulares que já estão pagos, "
              "quantos ainda estão pagando e alugados.")
        resultado_2()
        prosseguir = input("Aperte qualquer tecla para visualizar a proxima")
        os.system('cls')
        print("[3] - Quantos domicílios por cidade possuem banheiro e quantos não possuem.")
        resultado_3()
        prosseguir = input("Aperte qualquer tecla para visualizar a proxima")
        os.system('cls')
        print("[4] - A forma mais comum de abastecimento de água por cidade.")
        resultado_4()
        prosseguir = input("Aperte qualquer tecla para visualizar a proxima")
        os.system('cls')
        print("[5] - O percentual de domicílios por cidade que ainda não possuem energia elétrica")
        resultado_5()
        prosseguir = input("Aperte qualquer tecla para visualizar a proxima")
        os.system('cls')
        print("[6] - O percentual de moradores que participaram da entrevista por cor ou raça.")
        resultado_6()
        prosseguir = input("Aperte qualquer tecla para visualizar a proxima")
        os.system('cls')
        print("[7] - A região com maior número de municípios pesquisados.")
        resultado_7()
        prosseguir = input("Aperte qualquer tecla para continuar")
        os.system('cls')
    elif escolha == 9:
        print("Finalizando...")
        sys.exit()
def resultado_1():
    #Função que mostra o resultado da função-estatistica_1-
    resultado_estatistica_1 = estatistica_1()
    print(f"O numero de domicilios consultados é: {resultado_estatistica_1}")

def resultado_2():
    '''Função que mostra o resultado da função-estastistica_2-, caso o resultado seja uma lista
    ocorreu tudo bem com o arquivo, caso seja do tipo str sera a mensagem de erro,caso seja a mensagem
    de erro, o programa fecha'''
    import sys
    resultado_estatistica_2 = estatistica_2(dicionario_pesquisa())
    if type(resultado_estatistica_2) == list:
        print(f"Domicilios Particulares:\n{resultado_estatistica_2 [0]} estão pagos.\n"
              f"{resultado_estatistica_2 [1]} estão sendo pagos.\n"
              f"{resultado_estatistica_2 [2]} são alugados.")
    else:
        print(f"Erro: {resultado_estatistica_2}")
        sys.exit()#Erro encerrando o programa

def resultado_3():
    '''Função que mostra o resultado da função-estatistica_3-, caso o resultado seja um dicionario
    ocorreu tudo bem com o arquivo, caso contrario sera a mensagem de erro e o programa encerrra'''
    import sys
    resultado_estatistica_3 = estatistica_3(dicionario_pesquisa())
    if type(resultado_estatistica_3) == dict:
        for i in resultado_estatistica_3 :
            print(f"     Municipio de Codigo:{i}\n"
                  f"Residencia(s) com banheiro:{resultado_estatistica_3.get(i) [0]}\n"
                  f"Residencia(s) sem banheiro:{resultado_estatistica_3.get(i) [1]}\n")
    else:
        print(f"Erro: {resultado_estatistica_3}")
        sys.exit()#Erro encerrando o programa

def resultado_4():
    '''Função que mostra o resultado da função-estatistica_4-, caso o resultado seja um dicionario
        ocorreu tudo bem com o arquivo, caso contrario sera a mensagem de erro e o programa encerrra'''
    '''Pecorre o dicionario e imprime o resultado que mais se repete(moda)'''
    import sys
    from statistics import mode
    resultado_estatistica_4 = estatistica_4()
    if type(resultado_estatistica_4) == dict:
        for i in resultado_estatistica_4:
            mais_comum = 0
            aux = mode(resultado_estatistica_4.get(i))#Função moda da biblioteca statistics
            if aux == "1" :
                mais_comum = "REDE GERAL DE DISTRIBUIÇÃO"
            elif aux == "2" :
                mais_comum = "POÇO OU NASCENTE NA PROPRIEDADE"
            elif aux == "3" :
                mais_comum = "POÇO OU NASCENTE FORA DA PROPRIEDADE"
            elif aux == "4" :
                mais_comum = "CARRO-PIPA"
            elif aux == "5" :
                mais_comum = "ÁGUA DA CHUVA ARMAZENADA EM CISTERNA"
            elif aux == "6" :
                mais_comum = "ÁGUA DA CHUVA ARMAZENADA DE OUTRA FORMA"
            elif aux == "7" :
                mais_comum = "RIOS, AÇUDES, LAGOS E IGARAPÉS"
            elif aux == "8" :
                mais_comum = "OUTRA FORMA"
            elif aux == "9" :
                mais_comum = "POÇO OU NASCENTE NA ALDEIA"
            elif aux == "10" :
                mais_comum = "POÇO OU NASCENTE FORA DA ALDEIA"
            else :
                mais_comum = "resposta diferente"
            print(f"Municipio de Codigo:{i}\nForma mais comum de abastecimento: {mais_comum}.\n")
    else:
        print(f"Erro: {resultado_estatistica_4}")
        sys.exit()#Erro encerrando o programa

def resultado_5():
    '''Função que mostra o resultado da função-estatistica_5-, caso o resultado seja um dicionario
    ocorreu tudo bem com o arquivo, caso contrario sera a mensagem de erro e o programa encerrra'''
    import sys
    resultado_estatistica_5 = estatistica_5(dicionario_pesquisa())
    if type(resultado_estatistica_5) == dict:
        for i in resultado_estatistica_5:
            '''Pega o numero de domicilios da cidade sem energia e divide pelo total de domicilios da mesma
            para obter a porcentagem'''
            porcento_sem_energia = (resultado_estatistica_5.get(i)[2] * 100) / len(resultado_estatistica_5.get(i))
            print(f"     Municipio de Codigo:{i}\nPercentual de domicilios sem energia: {porcento_sem_energia:.1f}%.\n")
    else:
        print(f"Erro: {resultado_estatistica_5}")
        sys.exit()#Erro encerrando o programa

def resultado_6():
    '''Função que mostra o resultado da função-estatistica_4-, caso o resultado seja um dicionario
    ocorreu tudo bem com o arquivo, caso contrario sera a mensagem de erro e o programa encerrra'''
    import sys
    resultado_estatistica_6 = estatistica_6(dicionario_pesquisa())
    if type(resultado_estatistica_6) == dict:
        total = resultado_estatistica_6.get("total")
        '''Pega o numero de pessoas de determinada raça e divide pelo total de pessoas entrevistadas
        para obter o percentual dividido por raça'''
        percento_raça_branca = (resultado_estatistica_6.get("branca") [0] * 100) / total
        percento_raça_preta = (resultado_estatistica_6.get("preta") [0] * 100) / total
        percento_raça_amarela = (resultado_estatistica_6.get("amarela") [0] * 100) / total
        percento_raça_parda = (resultado_estatistica_6.get("parda") [0] * 100) / total
        percento_raça_indigena = (resultado_estatistica_6.get("indigena") [0] * 100) / total
        print(f"Das pessoas entrevistadas nessa pesquisa:\n\n"
              f"{percento_raça_branca:.1f}% são da raça branca.\n"
              f"{percento_raça_preta:.1f}% são da raça preta.\n"
              f"{percento_raça_amarela:.1f}% são da raça amarela.\n"
              f"{percento_raça_parda:.1f}% são da raça parda.\n"
              f"{percento_raça_indigena:.1f}% são da raça indigena")
    else:
        print(f"Erro: {resultado_estatistica_6}")
        sys.exit()#Erro encerrando o programa

def resultado_7():
    '''Função que mostra o resultado da função-estatistica_7-, caso o resultado seja um dicionario
    ocorreu tudo bem com o arquivo, caso contrario sera a mensagem de erro e o programa encerrra'''
    import sys
    resultado_estatistica_7 = estatistica_7(dicionario_pesquisa()).values()
    if type(resultado_estatistica_7) == str:
        print(f"Erro: {resultado_estatistica_7}")
        sys.exit() #Erro que encerra o programa
    else:
        #Compara os valores da lista para saber qual regiao mais pesquisada
        valor = list(resultado_estatistica_7)
        if valor [0] > valor [1] and valor [0] > valor [2] and valor [0] > valor [3] and valor [0] > valor [4] :
            print("Nessa pesquisa, encontra-se mais domicilios situados na região Nordeste")
        elif valor [1] > valor [0] and valor [1] > valor [2] and valor [1] > valor [3] and valor [1] > valor [4] :
            print("Nessa pesquisa, encontra-se mais domicilios situados na região Norte")
        elif valor [2] > valor [0] and valor [2] > valor [1] and valor [2] > valor [3] and valor [2] > valor [4] :
            print("Nessa pesquisa, encontra-se mais domicilios situados na região Centro-Oeste")
        elif valor [3] > valor [0] and valor [3] > valor [1] and valor [3] > valor [2] and valor [0] > valor [4] :
            print("Nessa pesquisa, encontra-se mais domicilios situados na região Sudeste")
        else :
            print("Nessa pesquisa, encontra-se mais domicilios situados na região Sul")

def main():
    #Função principal
    import os
    import sys
    print("===================================================================")
    print("               Bem Vindo ao CENSO DEMOGRAFICO 2020")
    print("        Aperte qualquer tecla para darmos inicio ao programa")
    print("===================================================================")
    input()
    os.system('cls')
    verificação_tecnicos = verificar_cadastros("tecnicos",dicionario_pesquisa())
    verificação_cidades = verificar_cadastros("cidades",dicionario_pesquisa())
    if type(verificação_tecnicos) == str :
        print(f"{verificação_tecnicos}, aperte qualquer tecla para prosseguir.")
        prosseguir = input("")
    else :
        print(f"O programa não reconhece os seguintes itens: {verificação_tecnicos},"
              f"corrija seu arquivo 'tecnicosIBGE'!")
        sys.exit()#Erro que fecha o programa
    if type(verificação_cidades) == str :
        print(f"{verificação_cidades}, aperte qualquer tecla para prosseguir. :)")
        prosseguir = input("")
    else :
        print(f"O programa não reconhece os seguintes itens: {verificação_cidades},"
              f"corrija seu arquivo 'regioes'!")
        sys.exit()#Erro que encerra o programa
    os.system('cls')
    continua = "S"#Looping do menu
    while continua == "S" or continua == "s":
        menu_opçoes()
        continua = opção_menu("continua")
        os.system('cls')
    print("Ate a proxima!")

main()