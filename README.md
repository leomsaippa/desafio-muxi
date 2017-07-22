[11] README com explicações sobre o projeto, decisões tomadas, como gerar o apk e como rodar os testes


# Desafio Muxi

O aplicativo busca uma lista de frutas em Json na Web, salva suas informações(nome,preço em dólar e URL de uma imagem) e calcula o preço em real de cada fruta de forma assíncrona utilizando código nativo.

## Instruções

As instruções a seguir são para utilizar uma cópia do projeto numa máquina local. Foi utilizada a plataforma Android Studio.

### Pré-requisitos

Pré-requisitos para utilizar o aplicativo e versões utilizadas. 

```
Android Studio 2.3+ (Versão utilizada 2.3.3)
NDK (Versão utilizada 15.1.4119039)
CMake
LLDB(Apenas para debug)
SDK (API Level entre 15 e 25)
```

### Instalação

Para instalar o NDK, CMake e LLDB -> http://tools.android.com/tech-docs/new-build-system/gradle-experimental/migrate-to-stable?hl=pt-br

Adicione o comando abaixo caso não haja ao build.gradle(Module app)
```
android{
 externalNativeBuild {
        cmake {
            path "CMakeLists.txt"
        }
    }
}

```

Verifique se o projeto já tem as dependências abaixos
Para adicionar as bibliotecas utilizadas, adicione ao build.gradle (Module app)
```
dependencies {
    compile 'com.mcxiaoke.volley:library:1.0.19'
    compile 'com.squareup.picasso:picasso:2.5.2'
}

```

Para adicionar o framework Junit e demais instrumentos de teste, adicione ao build.gradle(Module app)
```
dependencies {
    testCompile 'junit:junit:4.12'
    androidTestCompile 'com.android.support:support-annotations:25.0.1'
    androidTestCompile 'com.android.support.test:runner:0.5'
}
```

Para fazer uma cópia do projeto basta

```
$ git clone https://github.com/leomsaippa/desafio-muxi
```

## Desenvolvimento

O projeto foi desenvolvido utilizando integralmente a plataforma Android Studio. Foram definidas três prioridades: consumir o Json, utilizar código nativo para cálculo assíncrono e gerar uma lista de frutas que ao clicar, exibia mais informações.
A primeira prioridade implementada foi o consumo do Json. A princípio foi desenvolvido um WebService utilizando a biblioteca [kSoap](http://kobjects.org/ksoap2/index.html), porém, por limitações do Hardware, esta ideia foi descartada. 
Foi utilizado então a biblioteca [Volley](https://developer.android.com/training/volley/index.html) para auxiliar no consumo do Json e o resultado foi satisfatório.
A segunda prioridade implementada foi a utilização do código nativo. A princípio foi utilizado o [ndk-build](https://developer.android.com/ndk/guides/index.html?hl=pt-br), este foi executado sem problemas.
Apenas como facilitador, a nova estratégia foi utilizar o [CMake](https://cmake.org/) para auxiliar no uso de código nativo e o resultado foi satisfatório.
A terceira prioridade foi implementada utilizando a própria plataforma do Android Studio. Foi utilizado o [Material Design] (https://material.io/guidelines/) para definir um botão com um layout mais interessante e transições nas telas(limitadas as API > 20).
Algumas outras funcionalidades foram implementadas, como suporte a mudança de orientação de telas sem perder o estado atual.
Para isso foi adicionado ao arquivo AndroidManifest.xml o trecho abaixo para cada classe
```
<activity android:name=".Nome_Da_Classe"
            android:configChanges="orientation|screenSize">
</activity>
```
Para as imagens, foi utilizada a biblioteca [Picaso](http://square.github.io/picasso/), que por padrão tem o uso da cache. Ainda assim, foi utilizada uma estratégia para melhorar o uso da cache, mas não se mostrou eficiente em determinados erros de conexão e então foi mantida a cache definida por padrão.


## Gerando o APK

Para gerar o APK, utilizando o Android Studio vá Build > Generate Signed APK > Defina uma chave de segurança e o seu caminho > **Selecione as opções V1(Jar Signature) e V2(Full APK Signature)** > Finish.
  
Um exemplo do APK está no projeto na pasta Key>DesafioMuxi.apk. Basta instalá-lo, fornecendo as permissões necessárias de instalação de fontes desconhecidas.
## Rodando os testes

Foram definidos pequenos testes para verificar como o apicativo se comporta. Para utilizá-los no Android Studio vá app>java>nome_pacote(androidTest) e app>java>nome_pacote(test).
**Observação: Se for realizar os testes em um dispositivo, verifique se a tela está ligada para que os testes ocorram de forma correta. **

### Testes de tela

O teste de tela verifica se o texto no botão está correto e clica para mudar de tela. Na nova tela, verifica se o título está com o texto correto e então clica em cada um dos elementos da lista de frutas e na nova tela, verifica se o nome da fruta foi carregado de forma correta.


### Teste de código nativo

Foi definido um pequeno teste para verificar se o teste de código nativo estava fazendo os cálculos de forma correta. O teste consistia em chamar a função nativa 50 vezes e verificar se o retorno era o esperado.


## Ferramentas utilizadas

* [Junit](http://junit.org/junit4/) - Framework utilizado para testes
* [Volley](https://developer.android.com/training/volley/index.html) - Biblioteca para consumir o Json
* [Picaso](http://square.github.io/picasso/) - Biblioteca para manusear imagens 
* [Material Design] (https://material.io/guidelines/) - Linguagem de design desenvolvida pelo Google 
* [CMake](https://cmake.org/) - Plataforma para compilação de código nativo


## Autor

* **Leonardo Muniz Saippa** - [leomsaippa](https://github.com/leomsaippa)

