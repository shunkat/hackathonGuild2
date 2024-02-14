package guild.hackathon.di

import guild.hackathon.net.HackathonApi
import guild.hackathon.net.HackathonApiImpl
import guild.hackathon.net.UserApi
import guild.hackathon.net.UserApiImpl
import org.koin.dsl.module

val netModule = module {
    single<UserApi> { UserApiImpl(get()) }
    single<HackathonApi> { HackathonApiImpl(get()) }
}