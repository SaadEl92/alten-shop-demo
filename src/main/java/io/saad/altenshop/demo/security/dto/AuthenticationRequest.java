package io.saad.altenshop.demo.security.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record AuthenticationRequest (
		@Schema(example = "appuser@user.com") String email,
		@Schema(example = "123456") String password
) {}
