package com.ailtonluiz.sgdapi.core.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class SgdSecurity {

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public boolean isAuthenticated() {
        return getAuthentication().isAuthenticated();
    }

    public Long getUserId() {
        Jwt jwt = (Jwt) getAuthentication().getPrincipal();

        return jwt.getClaim("user_id");
    }

    /*  public boolean gerenciaRestaurante(Long restauranteId) {
          if (restauranteId == null) {
              return false;
          }

  Equal Authenticated user
          return restauranteRepository.existsResponsavel(restauranteId, getUsuarioId());
      }

      public boolean gerenciaRestauranteDoPedido(String codigoPedido) {
          return pedidoRepository.isPedidoGerenciadoPor(codigoPedido, getUsuarioId());
      }
  */
    public boolean equalAuthenticadedUser(Long userId) {
        return getUserId() != null && userId != null
                && getUserId().equals(userId);
    }

    public boolean hasAuthority(String authorityName) {
        return getAuthentication().getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals(authorityName));
    }

    public boolean hasScopeWrite() {
        return hasAuthority("SCOPE_WRITE");
    }

    public boolean hasScopeRead() {
        return hasAuthority("SCOPE_READ");
    }

    /*  public boolean podeGerenciarPedidos(String codigoPedido) {
          return temEscopoEscrita() && (hasAuthority("GERENCIAR_PEDIDOS")
                  || gerenciaRestauranteDoPedido(codigoPedido));
      }

      public boolean podeConsultarRestaurantes() {
          return temEscopoLeitura() && isAutenticado();
      }

      public boolean podeGerenciarCadastroRestaurantes() {
          return temEscopoEscrita() && hasAuthority("EDITAR_RESTAURANTES");
      }

      public boolean podeGerenciarFuncionamentoRestaurantes(Long restauranteId) {
          return temEscopoEscrita() && (hasAuthority("EDITAR_RESTAURANTES")
                  || gerenciaRestaurante(restauranteId));

                  can Consult Users Groups Permissions
      }
  */
    public boolean canConsultUsersGroupsPermissions() {
        return hasScopeRead() && hasAuthority("CONSULTAR_USUARIOS_GRUPOS_PERMISSOES");
    }

    public boolean canEditUsersGroupsPermissions() {
        return hasScopeWrite() && hasAuthority("EDITAR_USUARIOS_GRUPOS_PERMISSOES");
    }
//
//    public boolean podePesquisarPedidos(Long clienteId, Long restauranteId) {
//        return temEscopoLeitura() && (hasAuthority("CONSULTAR_PEDIDOS")
//                || usuarioAutenticadoIgual(clienteId) || gerenciaRestaurante(restauranteId));
//    }
    //can search for orders

    public boolean canSearchForOrders() {
        return isAuthenticated() && hasScopeRead();
    }


    public boolean canConsultPaymentMethods() {
        return isAuthenticated() && hasScopeRead();
    }

    public boolean canConsultCities() {

        return isAuthenticated() && hasScopeRead();
    }

    public boolean canConsultStates() {
        return isAuthenticated() && hasScopeRead();
    }


    public boolean canConsultStatistics() {
        return hasScopeRead() && hasAuthority("GERAR_RELATORIOS");
    }

}

