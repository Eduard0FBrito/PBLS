import random
import pickle
from Classe_Cartas import Cartas
from Classe_Jogador import Jogador
from prettytable import PrettyTable
import os
import time

'''
Autor: Eduardo Fernandes de Brito Pereira
Componente Curricular: Algoritmos I
Concluido em: 05/09/2019
Declaro que este código foi elaborado por mim de forma individual e não contém nenhum
trecho de código de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.
'''

def dicio_jogadores():
    #Função que abre o arquivo binario com o cadastro dos jogadores e retorna um dicionario
    file_cadastro = open("Jogadores_Cadastrados.dat", "rb")
    dicio_jogadores = pickle.load(file_cadastro)
    file_cadastro.close()
    return dicio_jogadores

def mostrarJogador(jogador):
    #Se o jogador ja tiver informações cadastradas, essas informações são mostradas
    print(f"{jogador.nome}")
    print(f"Partidas Jogadas: {jogador.partidas}")
    print(f"Partidas Vencidas: {jogador.vitorias}")
    print(f"Porcentagem de Vitorias: {jogador.porcentagem}%")

def buscar(dicionario, chave):
    #função que verifica se o jogador ja existe no dicionario
    return dicionario.get(chave)

def checar_no_arquivo(Nickname,dicio):
    #checar existencia do jogador para que possa mostras as informações ou não
    jogador = buscar(dicio,Nickname)
    if jogador:
        mostrarJogador(jogador)
        return True
    else:
        return False

def nick(player):
    #Função para pedir o nome dos jogadores
    try:
        if player == 1:
            Nickname = input("Player 1 --> Digite o seu Nickname:\n")
            if Nickname == "" or len(Nickname) <= 2:
                raise Exception
            else:
                return Nickname
        elif player == 2:
            Nickname = input("Player 2 --> Digite o seu Nickname:\n")
            if Nickname == "" or len(Nickname) <= 2:
                raise Exception
            else:
                return Nickname
    except:
        print("Numero minimo de Caracteres = 3 :)")
        return nick(player)

def jogadores_partida():
    '''Função que cria um arquivo com o nome dos jogadores que estão jogando a partida para ser utilizado depois
    na função de cadastro'''
    nick_jogadores = []
    file_jogadores = open("Jogando.txt", "r", encoding="UTF-8")
    for linha in file_jogadores:
        dividido = linha.strip()
        nick_jogadores.append(dividido)
    return nick_jogadores

def informação_jogador():
    '''Função que pega o nome dos jogadores, o player 2 nao consegue usar o mesmo nome que o P1, essa função também é
    responsavel por mostrar a informação dos jogadores caso ja tenham cadastro, caso não tenham essa função indica que
    é sua primeira partida'''
    lista_com_nicks = []
    for i in range(0,2,1):
        while True:
            Nickname = nick(i+1)
            if Nickname in lista_com_nicks:
                print("Player 1 ja está utilizando esse Nickname, tente outro!")
            else:
                break
        lista_com_nicks.append(Nickname)
        mostrar_informação = checar_no_arquivo(lista_com_nicks[i], dicio_jogadores())
        if not mostrar_informação:
            print(f"1º Partida de {lista_com_nicks[i]}")
            time.sleep(2)
            os.system("cls")
        else:
            time.sleep(2)
            os.system("cls")
        if i < 1:
            proximo_jogador = input("\nAperte qualquer tecla pra informar o Nickname do Jogador 2\n")
        else:
            começar_partida = input("\nAperte qualquer tecla pra escolher o modo de jogo\n")

    file_jogadores = open("Jogando.txt", "w", encoding="UTF-8")
    file_jogadores.write(f"{lista_com_nicks[0]}\n{lista_com_nicks[1]}")
    file_jogadores.close()
    os.system("cls")
    print(f"                      {lista_com_nicks[0]} X {lista_com_nicks[1]}")

def mostrar_cartas(baralho):
    #Função pra mostrar a mão do jogador utilizando a biblioteca Prettytable
    cartas = [""]
    personagens = ["Personagens"]
    valor = ["Valor"]
    força = ["Força"]
    energia = ["Energia"]
    jokempo = ["Jokempô"]

    for i in range(len(baralho)):
        cartas.append(f"Carta Nº {i + 1}")
        personagens.append(baralho[i].nome)
        valor.append(baralho[i].valor)
        força.append(baralho[i].força)
        energia.append(baralho[i].energia)
        jokempo.append(baralho[i].jaken)

    x = PrettyTable(cartas)
    x.padding_width = 1

    x.add_row(personagens)
    x.add_row(valor)
    x.add_row(força)
    x.add_row(energia)
    x.add_row(jokempo)

    print(x)

def lista_de_cartas():
    '''Função que pega cada linha do arquivo "Cartas.txt" e transforma em tipo um tipo de dado novo utilizando
    a classe Carta e retorna todas em uma lista'''
    lista_de_cartas = []
    with open("Cartas.txt", "r", encoding="UTF-8") as file_cartas :
        next(file_cartas)
        for linha in file_cartas :
            conteudo = linha.strip()
            dividido = conteudo.split(";")
            tipo_carta = Cartas(dividido)
            carta = [tipo_carta.nome,tipo_carta.valor,tipo_carta.força,tipo_carta.energia,tipo_carta.jaken]
            lista_de_cartas.append(Cartas(carta))
    return lista_de_cartas

def embaralhar(vetor):
    #Função que embaralha a lista de cartas
    tamanho = len(vetor)
    for i in range(0,tamanho): #Pecorre toda a lista
        indice = random.randint(0,tamanho) #Sorteia um numero que servira de posição
        carta_removida = vetor.pop(random.randint(0,tamanho-1)) #Sorteia uma carta para ser removida
        vetor.insert(indice,carta_removida) #Coloca a carta na posição sorteada
    return vetor

def deck_jogadores():
    #Função que pega a lista de carta e distribui pros outros jogadores
    pilha_embaralhada = embaralhar(lista_de_cartas()) #pilha de cartas
    baralho_1 = [] #Mão do jogador 1
    baralho_2 = [] #Mão do jogador 2
    for i in range(0,5,1):
        baralho_1.append(pilha_embaralhada.pop())
    for j in range(0,5,1):
        baralho_2.append(pilha_embaralhada.pop())
    return (pilha_embaralhada, baralho_1, baralho_2) #Retorno da pilha e das maõs dos jogadores

def escolher_tipo():
    #Função que assegura que apenas valores validos seram digitados
    try:
        escolha = int(input("Qual sua escolha?\n"))
        print()
        if escolha < 1 or escolha > 4:
            raise Exception
        else:
            return escolha
    except:
        print("Valor Invalido, tente novamente!")
        return escolher_tipo()

def escolher_carta(baralho):
    #Função que assegura que serão escolhidas apenas cartas existentes
    try:
        escolha = int(input("Com qual carta deseja disputar?\n"))
        print()
        if escolha < 1 or escolha > len(baralho):
            raise Exception
        else:
            return escolha - 1
    except:
        print("Carta Invalida, tente novamente")
        return escolher_carta(baralho)

def ordenar(vetor,tipo):
    #Função que ordena a mão do jogador de acordo com o tipo escolhido
    i = 0 #Inicia o I como 0
    while i < len(vetor) - 1: #Pecorre a lista ate o penultimo vetor
        menor = i #Diz que o menor elemento da lista é o I
        j = i + 1 #Inicia o J maior que o I
        while j < len(vetor): #Pecorre o vetor todo para fazer as comparações
            if tipo == 1: # Se for para ordernar o vetor pelo valor da carta
                if vetor[j].valor < vetor[menor].valor: #Compara os valores
                    menor = j
            elif tipo == 2: #Se for para ordenar o vetor pela força das cartas
                if vetor[j].força < vetor[menor].força: #Compara as forças
                    menor = j
            elif tipo == 3: #Se for para ordenar o vetor pela energia das cartas
                if vetor[j].energia < vetor[menor].energia: #Compara as energias
                    menor = j
            elif tipo == 4: #Se for para ordernar o vetor pelo Jokempo
                if vetor[j].jaken < vetor[menor].jaken: #Compara as palavras
                    menor = j
            else: #Se for para ordernar pelo nome
                if vetor [j].nome < vetor [menor].nome: #Compara as palavras e organiza em ordem alfabetica
                    menor = j
            j += 1
        if menor!=i:
            vetor[i],vetor[menor] = vetor[menor],vetor[i]
        i += 1
    return vetor

def sortear_valor(baralho):
    #Função que sorteia um valor baseado no tamanho da mão do jogador
    tamanho = (len(baralho) - 1)
    if tamanho == 1:
        return tamanho - 1
    else:
        return random.randint(0,tamanho)

def partida_aleatoria():
    #Função da partida aleatoria
    win = [0,0] #Lista para computar quantas partidas cada jogador venceu
    jogadores = jogadores_partida() #Lista com o nome dos jogadores
    pilha_jogo, baralho_1, baralho_2 = deck_jogadores() #Pilha e a mão dos jogadores
    escolheu_rodada = 2 #Variavel utilizada para alternar entre quem escolhe a rodada
    input("Aperte qualquer tecla para começar a partida :)\n") #Input vazio para começar o jogo
    for i in range(0,10,1): #O jogo consiste em 10 rodadas
        if escolheu_rodada == 2: #Se o jogador 2 tiver escolhido o tipo de rodada, então é vez do jogador 1 escolher
            print(f"{jogadores[0]} --> Escolha o tipo de disputa nessa rodada :)\n") #Jogador 1 escolhendo
            escolheu_rodada = 1 #Variavel atualizada para que o jogador 2 possa escolher na proxima rodada
        else: #Se o jogador 1 tiver escolhido, então é vez do 2 escolher
            print(f"{jogadores[1]} --> Escolha o tipo de disputa nessa rodada :)\n") #Jogador 2 escolhendo
            escolheu_rodada = 2 #Variavel atualizada para que o jogador 1 possa escolher na proxima
        print("[1] - VALOR\n[2] - FORÇA\n[3] - ENERGIA\n[4] - JOKEMPÔ\n")
        if win [0] > 1 : #Caso o jogador 1 ainda tenha vencido mais de uma rodada
            print(f"{jogadores [0]} Venceu {win [0]} Rodadas :)") #Imprime essa mensagem
        elif win [0]==1 : #Caso so tenha vencido uma
            print(f"{jogadores [0]} Venceu {win [0]} Rodada :)") #Imprime essa mensagem
        elif win [0]==0 : #Caso não tenha vencido
            print(f"{jogadores [0]} Ainda Não Venceu :(") #Imprime essa mensagem
        #Analoga para o jogador 2
        if win [1] > 1 :
            print(f"{jogadores [1]} Venceu {win [1]} Rodadas :)")
        elif win [1]==1 :
            print(f"{jogadores [1]} Venceu {win [1]} Rodada :)")
        elif win [1]==0 :
            print(f"{jogadores [1]} Ainda Não Venceu :(")
        escolha = escolher_tipo() #Chamada da função que escolhe o tipo de rodada
        if escolha == 1:
            print("[1] - VALOR")
            baralho_1,baralho_2 = rodada_aleatoria(pilha_jogo,baralho_1,baralho_2, 1, i,jogadores[0],jogadores[1],win)
            #Chamada da função de rodada aleatoria e ordenando por valor
        elif escolha == 2:
            print("[2] - FORÇA")
            baralho_1, baralho_2 = rodada_aleatoria(pilha_jogo,baralho_1,baralho_2, 2, i,jogadores[0],jogadores[1],win)
            #Chamada da função de rodada aleatoria e ordenando por força
        elif escolha == 3:
            print("[3] - ENERGIA")
            baralho_1, baralho_2 = rodada_aleatoria(pilha_jogo,baralho_1,baralho_2, 3, i,jogadores[0],jogadores[1],win)
            #Chamada de função de rodada aleatoria e ordenando por energia
        elif escolha == 4:
            print("[4] - JOKEMPÔ")
            baralho_1, baralho_2 = rodada_aleatoria(pilha_jogo,baralho_1,baralho_2, 4, i,jogadores[0],jogadores[1],win)
            #Chamada da função de rodada aleatoria e ordenando por Jaken

        if baralho_1 == []: #Se o baralho do Jogador 1 estiver vazio, ele venceu o jogo
            print(f"{jogadores[0]} WINNER!!")
            cadastro_jogador(dicio_jogadores(),jogadores[0]) #Cadastra os jogadores
            break #Para o looping e declara o vencendor

        elif baralho_2 == []: #Analago para o jogador 2
            print(f"{jogadores[1]} WINNER!!")
            cadastro_jogador(dicio_jogadores(), jogadores [1])
            break

    if len(baralho_1) > 0 and len(baralho_2) > 0: #Se as 10 rodadas terminarem e nenhum jogador tenha vencido
        resultado = deu_empate(baralho_1,baralho_2) #Chamamos a função de desempate
        if resultado == "Jogador_1": #Caso o jogador 1 tenha vencido
            print(f"{jogadores[0]} WINNER!!")
            cadastro_jogador(dicio_jogadores(), jogadores[0]) #Cadastra os jogadores
        elif resultado == "Jogador_2": #Analoga para o jogador 2
            print(f"{jogadores[1]} WINNER!!")
            cadastro_jogador(dicio_jogadores(), jogadores [1])
        else: #Caso o empate permaneça, ele é informado
            print(f"O confronto entre {jogadores[0]} x {jogadores[1]} terminou em empate :(")
            cadastro_jogador(dicio_jogadores(), "empate") #Cadastra os jogadores

def partida_manual():
    #Função da partida manual
    win = [0,0] #Lista para computar quantas partidas cada jogador venceu
    jogadores = jogadores_partida() #Lista com o nome dos jogadores
    pilha_jogo, baralho_1, baralho_2 = deck_jogadores() #pilha e baralho dos jogadores
    escolheu_rodada = 2 #Variavel utilizada para alternar entre quem escolhe a rodada
    input("Aperte qualquer tecla para começar a partida :)\n") #Input vazio para começar o jogo
    for i in range(0,10,1): #O jogo consiste em 10 rodadas
        if escolheu_rodada == 2: #Se o jogador 2 tiver escolhido o tipo de rodada, então é vez do jogador 1 escolher
            print(f"{jogadores[0]} --> Escolha o tipo de disputa nessa rodada :)\n")#Jogador 1 escolhendo
            escolheu_rodada = 1 #Atualizando variavel para que o jogador 2 possa escolher a proxima rodada
        else: #Se o jogador 1 tiver escolhido o tipo de rodada, então é vez do jogador 2 escolher
            print(f"{jogadores[1]} --> Escolha o tipo de disputa nessa rodada :)\n")
            escolheu_rodada = 2 #Atualizando variavel para que o jogador 1 possa escolher a proxima rodada
        print("[1] - VALOR\n[2] - FORÇA\n[3] - ENERGIA\n[4] - JOKEMPÔ\n")
        if win[0] > 1:
            print(f"{jogadores [0]} Venceu {win [0]} Rodadas :)")
        elif win[0] == 1:
            print(f"{jogadores [0]} Venceu {win [0]} Rodada :)")
        elif win[0] == 0:
            print(f"{jogadores[0]} Ainda Não Venceu :(")

        if win[1] > 1:
            print(f"{jogadores[1]} Venceu {win [1]} Rodadas :)")
        elif win[1] == 1:
            print(f"{jogadores[1]} Venceu {win[1]} Rodada :)")
        elif win[1] == 0:
            print(f"{jogadores[1]} Ainda Não Venceu :(")
        escolha = escolher_tipo()
        #Chamadas da função de rodada manual ordenando em ordem alfabetica
        if escolha == 1:
            print("[1] - VALOR") #Comparando valor
            baralho_1,baralho_2 = rodada_manual(pilha_jogo,baralho_1,baralho_2,1,i,jogadores[0],jogadores[1],win)

        elif escolha==2 :
            print("[2] - FORÇA") #Comparando força
            baralho_1,baralho_2 = rodada_manual(pilha_jogo,baralho_1,baralho_2,2,i,jogadores[0],jogadores[1],win)

        elif escolha==3 :
            print("[3] - ENERGIA") #Comparando energia
            baralho_1,baralho_2 = rodada_manual(pilha_jogo,baralho_1,baralho_2,3,i,jogadores[0],jogadores[1],win)

        elif escolha==4 :
            print("[4] - JOKEMPÔ") #Comparando jokempo
            baralho_1,baralho_2 = rodada_manual(pilha_jogo,baralho_1,baralho_2,4,i,jogadores[0],jogadores[1],win)

        #Se algum baralho estiver vazio então aquele jogador venceu o jogo
        if baralho_1 == []:
            print(f"{jogadores [0]} WINNER!!")
            cadastro_jogador(dicio_jogadores(), jogadores [0])
            break

        elif baralho_2 == []:
            print(f"{jogadores [1]} WINNER!!")
            cadastro_jogador(dicio_jogadores(), jogadores [1])
            break

    #Se os dois baralhos ainda estiverem com cartas, a função de desempate é chamada
    if len(baralho_1) > 0 and len(baralho_2) > 0:
        resultado = deu_empate(baralho_1, baralho_2)
        if resultado=="Jogador_1" :
            print(f"{jogadores [0]} WINNER!!")
            cadastro_jogador(dicio_jogadores(), jogadores [0])
        elif resultado=="Jogador_2" :
            print(f"{jogadores [1]} WINNER!!")
            cadastro_jogador(dicio_jogadores(), jogadores [1])
        else :
            print(f"O confronto entre {jogadores [0]} x {jogadores [1]} terminou em empate :(")
            cadastro_jogador(dicio_jogadores(), "empate")

def rodada_manual(pilha_jogo,baralho_1,baralho_2,tipo,num_rodada,nick1,nick2,win):
    #Função da rodada manual, onde o jogador escolhe com qual carta ele ira disputar cada rodada
    baralho_1 = ordenar(baralho_1, 0) #Ordenando o baralho de acordo com a disputa escolhida
    prosseguir = input(f" {nick1} --> Aperte qualquer tecla para ver suas cartas\n")
    mostrar_cartas(baralho_1) #Mostrando mão do jogador 1
    num_1 = escolher_carta(baralho_1) #Jogador 1 escolhendo com qual carta vai disputar
    baralho_2 = ordenar(baralho_2, 0) #Ordenando o baralho de acordo com a disputa escolhida
    prosseguir = input(f" {nick2} --> Aperte qualquer tecla para ver suas cartas\n")
    mostrar_cartas(baralho_2) #Mostrando mão do jogador 2
    num_2 = escolher_carta(baralho_2) #Jogador 2 escolhendo com qual carta ir disputar
    resultado_disputa = venceu_rodada(tipo,baralho_1,baralho_2,nick1,nick2,num_1,num_2) #Chamada da função
    baralho_1,baralho_2 = resultado_rodada(pilha_jogo,baralho_1,baralho_2,resultado_disputa,num_rodada,nick1,nick2,win)
    return (baralho_1, baralho_2)

def deu_empate(baralho_1, baralho_2):
    resultado_jogo = final_de_jogo(baralho_1, baralho_2)
    if resultado_jogo == "Venceu_1" :
        return "Jogador_1"
    elif resultado_jogo == "Venceu_2" :
        return "Jogador_2"
    else :
        return "Empatou"

def final_de_jogo(baralho_1,baralho_2):
    #Função de desempate
    atributo_1 = [0,0,0] #Lista que armazena a soma dos valores, soma das forças e soma das energias da cartas do J1
    atributo_2 = [0,0,0] #Lista do jogador 2
    for carta in baralho_1: #Pecorre a mão do jogador 1
        atributo_1[0] += carta.valor #soma os valores
        atributo_1[1] += carta.força #soma as forças
        atributo_1[2] += carta.energia #soma as energias
    for carta in baralho_2: #Pecorre a mão do jogador 2
        atributo_2[0] += carta.valor #Soma os valores
        atributo_2[1] += carta.força #Soma as forças
        atributo_2[2] += carta.energia #Soma as energias
    if atributo_1[0] < atributo_2[0]: #a menor soma de valores vence
        return "Venceu_1"
    elif atributo_1[0] > atributo_1[0]:
        return "Venceu_2"
    else: #Caso contrario, compara-se as forças
        #a menor soma de forças vence
        if atributo_1[1] < atributo_2[1]:
            return "Venceu_1"
        elif atributo_1[1] > atributo_2[1]:
            return "Venceu_2"
        else: #Caso contrario, compara-se as energias
            #a menor soma de energias vence
            if atributo_1[2] < atributo_2[2]:
                return "Venceu_1"
            elif atributo_1[2] > atributo_2[2]:
                return "Venceu_2"
            else:
                return "Empate"

def rodada_aleatoria(pilha_jogo,baralho_1,baralho_2,tipo,num_rodada,nick1,nick2,win):
    baralho_1 = ordenar(baralho_1, tipo) #Ordenando o baralho de acordo com a disputa
    prosseguir = input(f"\n{nick1} --> Aperte qualquer tecla para ver suas cartas\n")
    mostrar_cartas(baralho_1) #Mostrando mão do jogador 1
    num_1 = sortear_valor(baralho_1) #Sorteando a carta com qual o jogador 1 ira disputar
    baralho_2 = ordenar(baralho_2, tipo) #Ordenando o baralho de acordo com a disputa
    prosseguir = input(f"\n{nick2} --> Aperte qualquer tecla para ver suas cartas\n")
    mostrar_cartas(baralho_2) #Mostrando mão do jogador 2
    num_2 = sortear_valor(baralho_2) #Sorteando a carta com qual o jogador 2 ira disputar
    resultado_disputa = venceu_rodada(tipo,baralho_1,baralho_2,nick1,nick2,num_1,num_2) #Chamada da função
    baralho_1, baralho_2 = resultado_rodada(pilha_jogo,baralho_1,baralho_2,resultado_disputa,num_rodada,nick1,nick2,win)
    return (baralho_1, baralho_2)

def remover_carta(baralho,nome,valor):
    #Função que remove a carta com qual o jogador disputou
    for i in range(len(baralho)):
        if baralho[i].nome == nome and baralho[i].valor == valor:
            indice = i
    baralho.pop(indice)
    return baralho

def resultado_rodada(pilha,baralho_1,baralho_2,resultado,num_rodada,nick1,nick2,win):
    #Função que retorna o resultado da rodada
    if resultado [0] == "venceu_1": #Caso o jogador 1 tenha vencido
        win[0] += 1 #Aumenta 1 na sua lista de vitorias
        print(f" {nick1} venceu a {num_rodada + 1}º rodada!")
        baralho_1 = remover_carta(baralho_1, resultado[1], resultado[2]) #Remove a carta com qual o jogador 1 disputou
        baralho_2 = remover_carta(baralho_2, resultado[3], resultado[4]) #Remove a carta com qual o jogador 2 disputou
        baralho_2.append(pilha.pop()) #Adiciona outra carta na mão do jogador que perdeu a rodada
        return (baralho_1,baralho_2) #Retorna os baralhos
    elif resultado [0] == "venceu_2": #Caso o jogador 2 tenha vencido
        win[1] += 1 #Aumenta 1 na lista de vitorias do jogador 2
        print(f" {nick2} venceu a {num_rodada + 1}º rodada!")
        baralho_1 = remover_carta(baralho_1, resultado[1], resultado[2]) #Remove a carta com qual o jogador 1 disputou
        baralho_2 = remover_carta(baralho_2, resultado[3], resultado[4]) #Remove a carta com qual o jogador 2 disputou
        baralho_1.append(pilha.pop()) #Adiciona outra carta na mão do jogador que perdeu a rodada
        return (baralho_1,baralho_2) #Retorna os baralhos
    else: #Caso a rodada resulte em empate
        print(f"Ocorreu um empate na {num_rodada + 1}º rodada!")
        baralho_1 = remover_carta(baralho_1, resultado[1], resultado[2]) #Remove a carta com qual o jogador 1 disputou
        baralho_2 = remover_carta(baralho_2, resultado[3], resultado[4]) #Remove a carta com qual o jogador 2 disputou
        baralho_1.append(pilha.pop())
        baralho_2.append(pilha.pop())
        # Adiciona uma nova carta na mão dos dois jogadores
        return (baralho_1, baralho_2) #Retorna os baralhos

def venceu_rodada(tipo,baralho_1,baralho_2,nick1,nick2,num_1,num_2):
    #Função que compara os atributos para saber quem vence a rodada
    nome1 = baralho_1[num_1].nome #guarda o nome da carta com qual o jogador 1 disputara
    valor1 = baralho_1[num_1].valor #guarda o valor da carta com qual o jogador 2 disputara
    nome2 = baralho_2[num_2].nome #guarda o nome da carta do jogador 2
    valor2 = baralho_2[num_2].valor #guarda o valor da carta do jogador 2
    print(f"{nick1} disputará com {nome1}!")
    print(f"{nick2} disputará com {nome2}!")
    if tipo == 1: #Se for disputa de valores
        if valor1 > valor2:
            # A carta com maior vence
            return ("venceu_1",nome1,valor1,nome2,valor2)
        elif valor2 > valor1:
            return ("venceu_2",nome1,valor1,nome2,valor2)
        else: #Caso contrario, acontece empate
            return ("empate",nome1,valor1,nome2,valor2)
    elif tipo == 2: #Se for disputa de forças
        if baralho_1[num_1].força > baralho_2[num_2].força:
            # A carta com maior força vence
            return ("venceu_1",nome1,valor1,nome2,valor2)
        elif baralho_2[num_2].força > baralho_1[num_1].força:
            return ("venceu_2",nome1,valor1,nome2,valor2)
        else: #Caso contrario, acontece empate
            return ("empate",nome1,valor1,nome2,valor2)
    elif tipo == 3: #Se for disputa de energia
        if baralho_1[num_1].energia > baralho_2[num_2].energia:
            #A carta com maior energia vence
            return ("venceu_1",nome1,valor1,nome2,valor2)
        elif baralho_2[num_2].energia > baralho_1[num_1].energia:
            return ("venceu_2",nome1,valor1,nome2,valor2)
        else: #Caso contrario, acontece empate
            return ("empate",nome1,valor1,nome2,valor2)
    else: #Se for disputa Jokempo
        embaralhar(baralho_1) #Embaralha a mão do jogador 1
        embaralhar(baralho_2) #Embaralha a mão do jogador 2
        resultado_joken = joken(baralho_1,baralho_2,num_1,num_2) #Chamada da função de resultado do jokempo
        if resultado_joken == "Venceu_1":
            return ("venceu_1",nome1,valor1,nome2,valor2)
        elif resultado_joken == "Venceu_2":
            return ("venceu_2",nome1,valor1,nome2,valor2)
        else:
            return ("empate",nome1,valor1,nome2,valor2)

def joken(baralho_1,baralho_2,num1,num2):
    '''Resultado do jokempo, mesmas regras do pedra, papel e tesoura
    pedra vence de tesoura, perde para papel e empata com pedra'''
    if baralho_1[num1].jaken == "Tesoura":
        if baralho_2[num2].jaken == "Pedra":
            return "Venceu_2"
        elif baralho_2[num2].jaken == "Papel":
            return "Venceu_1"
        else:
            return "Empate"
    elif baralho_1[num1].jaken == "Pedra":
        if baralho_2[num2].jaken == "Papel":
            return "Venceu_2"
        elif baralho_2[num2].jaken == "Tesoura":
            return "Venceu_1"
        else:
            return "Empate"
    else:
        if baralho_2[num2].jaken == "Tesoura":
            return "Venceu_2"
        elif baralho_2[num2].jaken == "Pedra":
            return "Venceu_1"
        else:
            return "Empate"

def continuar_jogando():
    #Função de verificação de respostas
    try:
        valores_aceitos = ["s","S","N","n"] #Essas são as respostas aceitas
        escolha = input("Deseja jogar novamente?(S/N)") #Escolha sim ou não
        if escolha not in valores_aceitos: #Se a escolha não estiver na lista de valores aceitos
            raise Exception #Força o erro
        else: #Caso contrario
            return escolha #retorna o valor escolhido
    except: # caso aconteça erro
        print("Opção Invalida, tente novamente!")
        return continuar_jogando() #A função é chamada novamente

def escolher_valores() :
    #Função que garante valores validos
    try :
        escolha = int(input("Qual sua escolha?\n"))
        if escolha < 1 and escolha > 2 : #se o valor escolhido não estiver entre 1 e 2
            raise Exception  #Força o erro
        else : #Caso contrario é um valor valido
            return escolha
    except : #Caso aconteça erro
        print("Opção Incorreta, tente novamente")
        return escolher_valores() #A função é chamada novamente

def cadastro_jogador(dicionario,vitoria):
    #Função de cadastro dos jogadores
    jogadores = jogadores_partida() #Guarda o nome dos jogadores
    for i in range(0,2,1): #Pecorre o dicionario 2x
        jogador = buscar(dicionario,jogadores[i]) #Busca o primeiro jogador no dicionario
        if jogador: #Caso ele seja encontrado
            jogador.partidas += 1 #seu numero de partidas é atulizado
            if vitoria == jogador.nome: #Caso ele tenha vencido a partida
                jogador.vitorias += 1 #Seu numero de vitorias é atualizado
            jogador.porcentagem = (jogador.vitorias*100)/jogador.partidas #Sua porcentagem de vitorias é atualizada
        else: #Caso nem seja encontrado, um dicionario pra esse jogador sera criado
            if vitoria == jogadores[i]:  #Caso ela tenha vencido a partida
                num_vitoria = 1 #seu dicionario ja começara com 1 vitoria
            else: #Caso contrario
                num_vitoria = 0 #suas vitorias começam em 0
            partida = 1 #Suas partidas começam em 1
            porcentagem = num_vitoria*100/partida #e sua porcentagem é calculada
            jogador = Jogador(jogadores[i],partida,num_vitoria,porcentagem) #é criado um objeto do tipo jogador
            dicionario[jogadores[i]] = jogador #O novo jogador é adicionado no dicionario de objetos
    file_cadastro = open("Jogadores_Cadastrados.dat", "wb")
    pickle.dump(dicionario, file_cadastro) # O arquivo binario é atualizado
    file_cadastro.close()

def menu():
    while True: #Looping infinito
        print("[1] - Jogar\n[2] - Sair do Jogo")
        escolha = escolher_valores()
        if escolha == 2: #Caso escolha sair do jogo
            print("Saindo...")
            break # O looping é quebrado
        else: #Se ele escolher jogar
            os.system("cls")
            informação_jogador() #Chamada da função que pede o nome e mostra as informações dos jogadores
            print("[1] - Modo Aleatorio\n[2] - Modo Manual")
            modo_jogo = escolher_valores() #Chamada da função que o jogador escolher o modo de jogo
            if modo_jogo == 1: #Se for modo aleatorio
                partida_aleatoria() #chamada da função de partida aleatoria
            elif modo_jogo == 2: #Se for modo manual
                partida_manual() #chamada da função de partida manual
            escolha = continuar_jogando() #ao fim da partida, o jogador escolher se quer jogar outra partida ou não
            if escolha == "S" or escolha == "s": #Se aceitar jogar outra
                print("Carregando...") #printa essa mensagem e inicia outra partida
            else: #Caso contrario
                print("Ate a proxima! :)")
                break #quebra o looping e fecha o programa

menu()